package com.example.goblidas_backend.jwt;

import com.example.goblidas_backend.services.UserService;
import com.example.goblidas_backend.services.UserServiceImpl;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;

@Component
//@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserServiceImpl userService;


    public JwtAuthFilter(JwtUtil jwtUtil, UserServiceImpl userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException, java.io.IOException {

        String path = request.getServletPath();
        final String requestPath = request.getRequestURI();

        List<String> publicPaths = List.of(
                "/auth/",
                "/auth/login",
                "/auth/register",
                "/api/product/paged",
                "/api/image/paged",
                "/api/user/register"
        );

        if(publicPaths.stream().anyMatch(path::equals)) {

            filterChain.doFilter(request, response);
            return;
        }
        //if(path.startsWith("/auth/")) {
        //    filterChain.doFilter(request, response);
        //    return;
        //}

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        userEmail = jwtUtil.extractUsername(jwt);

        if(requestPath.startsWith("auth/login")) {
            filterChain.doFilter(request, response);
        }

        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = userService.loadUserByUsername(userEmail);

            if(jwtUtil.isTokenValid(jwt, userDetails)){
                var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }
        filterChain.doFilter(request, response);
    }

}
