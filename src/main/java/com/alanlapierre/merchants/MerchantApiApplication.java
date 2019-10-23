package com.alanlapierre.merchants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MerchantApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MerchantApiApplication.class, args);
	}

}
