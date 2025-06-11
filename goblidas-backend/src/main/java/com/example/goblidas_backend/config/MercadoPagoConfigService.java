package com.example.goblidas_backend.config;

import com.mercadopago.MercadoPagoConfig;
//import org.eclipse.sisu.PostConstruct;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Configuration
public class MercadoPagoConfigService {

    @Value("${mercadopago.access.token}")
    private String accessToken;

    @PostConstruct
    public void configureMercadoPago(){

        if (accessToken == null || accessToken.isEmpty()) {
            throw new IllegalStateException("El access token de mercado pago no esta configurado");
        }


        MercadoPagoConfig.setAccessToken(accessToken);
        System.out.println("Token de mp configurado exitosamente" + accessToken);
    }
}
