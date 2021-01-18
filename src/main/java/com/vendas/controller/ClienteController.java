package com.vendas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.vendas.entity.Cliente;
import com.vendas.repository.ClientesRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	private ClientesRepository clientesRepository;

	public ClienteController(ClientesRepository clientesRepository) {
		this.clientesRepository = clientesRepository;
	}
	
	@GetMapping("{id}")
	public Cliente getClientesById(@PathVariable Integer id) {
		return clientesRepository
					.findById(id)
					.orElseThrow(() -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Cliente save(@RequestBody Cliente cliente) {
		return clientesRepository.save(cliente);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable Integer id) {
		Optional<Cliente> cliente = clientesRepository.findById(id);
		
		if(cliente.isPresent()) {
			clientesRepository.delete(cliente.get());
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("{id}")
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
	
	@GetMapping
	public ResponseEntity find(Cliente filtro) {
		ExampleMatcher matcher = ExampleMatcher
									.matching()
									.withIgnoreCase()
									.withStringMatcher(StringMatcher.CONTAINING);
		Example example = Example.of(filtro, matcher);
		List<Cliente> lista = clientesRepository.findAll(example);
		return ResponseEntity.ok(lista);
	}
}
