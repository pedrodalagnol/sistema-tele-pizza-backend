package com.grupo11.sistema_tele_pizza_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.grupo11.sistema_tele_pizza_backend.dominio.dados")
@EntityScan(basePackages = "com.grupo11.sistema_tele_pizza_backend.dominio.entidades")
public class SistemaTelePizzaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaTelePizzaBackendApplication.class, args);
	}

}
