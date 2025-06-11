package com.example.goblidas_backend;

import com.mercadopago.MercadoPagoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GoblidasBackendApplication {

	public static void main(String[] args) {
		//
		SpringApplication.run(GoblidasBackendApplication.class, args);
		//System.out.println(MercadoPagoConfig.getAccessToken());
	}

}
