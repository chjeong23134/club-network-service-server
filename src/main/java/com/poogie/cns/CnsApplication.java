package com.poogie.cns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CnsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CnsApplication.class, args);
	}

}
