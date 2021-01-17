package com.vendas;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import com.vendas.entity.Cliente;
import com.vendas.entity.Pedido;
import com.vendas.repository.ClientesRepository;
import com.vendas.repository.PedidosRepository;

@SpringBootApplication
@RestController
public class VendasApplication {

	@Bean
	public CommandLineRunner init(@Autowired ClientesRepository clienteRepository,
			@Autowired PedidosRepository pedidosRepository) {
		return args -> {
			Cliente cliente = new Cliente();
			cliente.setNome("douglas");
			clienteRepository.save(cliente);
			
			Pedido p = new Pedido();
			p.setCliente(cliente);
			p.setDataPedido(LocalDate.now());
			p.setTotal(BigDecimal.valueOf(1000));
			
			pedidosRepository.save(p);

			Cliente clientePedidos = clienteRepository.findClienteFetchPedidos(cliente.getId());
			System.out.println(clientePedidos);
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
