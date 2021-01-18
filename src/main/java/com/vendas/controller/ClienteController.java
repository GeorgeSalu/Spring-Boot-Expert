package com.vendas.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("/api/clientes")
	public ResponseEntity save(@RequestBody Cliente cliente) {
		Cliente clienteSalvo = clientesRepository.save(cliente);
		return ResponseEntity.ok(clienteSalvo);
	}
	
	@DeleteMapping("/api/clientes/{id}")
	@ResponseBody
	public ResponseEntity delete(@PathVariable Integer id) {
		Optional<Cliente> cliente = clientesRepository.findById(id);
		
		if(cliente.isPresent()) {
			clientesRepository.delete(cliente.get());
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/api/clientes/{id}")
	@ResponseBody
	public ResponseEntity update(@PathVariable Integer id,
								@RequestBody Cliente cliente) {
		return clientesRepository
					.findById(id)
					.map(clienteExistente -> {
						cliente.setId(clienteExistente.getId());
						clientesRepository.save(cliente);
						return ResponseEntity.noContent().build();
					}).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
}
