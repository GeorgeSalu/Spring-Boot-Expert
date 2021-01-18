package com.vendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import com.vendas.entity.Cliente;
import com.vendas.repository.ClientesRepository;

@SpringBootApplication
@RestController
public class VendasApplication {
	
	@Bean
	public CommandLineRunner commandLineRunner(@Autowired ClientesRepository clientesRepository) {
		return args -> {
			Cliente cliente = new Cliente(null, "Fulano");
			clientesRepository.save(cliente);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
