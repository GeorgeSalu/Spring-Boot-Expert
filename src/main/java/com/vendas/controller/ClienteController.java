package com.vendas.controller;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
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
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		clientesRepository.findById(id)
			.map(cliente -> {
				clientesRepository.delete(cliente);
				return cliente;
			}).orElseThrow(() -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	@PutMapping("{id}")
	public void update(@PathVariable Integer id,
								@RequestBody Cliente cliente) {
		 clientesRepository
					.findById(id)
					.map(clienteExistente -> {
						cliente.setId(clienteExistente.getId());
						clientesRepository.save(cliente);
						return clienteExistente;
					}).orElseThrow(() -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	@GetMapping
	public List<Cliente> find(Cliente filtro) {
		ExampleMatcher matcher = ExampleMatcher
									.matching()
									.withIgnoreCase()
									.withStringMatcher(StringMatcher.CONTAINING);
		Example example = Example.of(filtro, matcher);
		return clientesRepository.findAll(example);
	}
}
