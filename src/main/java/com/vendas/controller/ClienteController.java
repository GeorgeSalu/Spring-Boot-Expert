package com.vendas.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vendas.entity.Cliente;
import com.vendas.repository.ClientesRepository;

@Controller
public class ClienteController {

	private ClientesRepository clientesRepository;

	public ClienteController(ClientesRepository clientesRepository) {
		this.clientesRepository = clientesRepository;
	}
	
	@GetMapping("/api/clientes/{id}")
	@ResponseBody
	public ResponseEntity<Cliente> helloClientes(@PathVariable Integer id) {
		Optional<Cliente> cliente = clientesRepository.findById(id);
		
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.notFound().build();
	}
	
}
