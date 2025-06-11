package com.example.goblidas_backend.config;

import com.example.goblidas_backend.jwt.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationProvider authenticationProvider, JwtAuthFilter jwtAuthFilter) throws Exception {
        http    .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/product").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/category").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/order").permitAll()
                        .requestMatchers(HttpMethod.POST, "api/order/create").authenticated()
                        .requestMatchers(HttpMethod.GET, "api/orderdetail").permitAll()
                        .requestMatchers(HttpMethod.POST, "api/orderdetail").authenticated()
                        .requestMatchers(HttpMethod.GET, "api/orderdetailid").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/price").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/size").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/useradress").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/user").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/detail").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/discount").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/discountprice").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/discountpriceid").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/image").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/adress").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/detailprice").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/detailpriceid").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/product/filter").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/useradress/filter").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/useradress/filter**").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/product/paged").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/order/user/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "api/detail/**").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/fromadmin").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/webhook/**").permitAll()       // webhook público para MP
                        .requestMatchers("/api/payment/**").authenticated()
                        .requestMatchers("/api/payment/create").authenticated()
                        .anyRequest().authenticated())
                .sessionManagement(sess -> sess.
                        sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
                //.build();

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
}
