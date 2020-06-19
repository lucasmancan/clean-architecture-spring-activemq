package br.com.alelo.estrutura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class EstruturaApplication {
	public static void main(String[] args) {
		SpringApplication.run(EstruturaApplication.class, args);
	}
}
