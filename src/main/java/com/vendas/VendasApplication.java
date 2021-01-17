package com.vendas;

import java.util.List;

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
	public CommandLineRunner init(@Autowired ClientesRepository clienteRepository) {
		return args -> {
			Cliente cliente = new Cliente();
			cliente.setNome("douglas");
			clienteRepository.save(cliente);
			
			Cliente outroCliente = new Cliente();
			outroCliente.setNome("douglas 2");
			clienteRepository.save(outroCliente);
			
			
			List<Cliente> todosClientes = clienteRepository.findAll();
			todosClientes.forEach(System.out::println);
			
			todosClientes.forEach(c -> {
				c.setNome(c.getNome() + " atualizado");
				clienteRepository.save(c);
			});
			
			todosClientes = clienteRepository.findAll();
			todosClientes.forEach(System.out::println);
			
			boolean existsByNome = clienteRepository.existsByNome("douglas");
			System.out.println(existsByNome);
			
			List<Cliente> encontrarPorNome = clienteRepository.encontrarPorNome("douglas atualizado");
			encontrarPorNome.forEach(System.out::println);
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
