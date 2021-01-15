package com.vendas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendas.model.Cliente;
import com.vendas.repository.ClienteRepository;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	public void salvarCliente(Cliente cliente) {
		validarCliente(cliente);
		clienteRepository.persistir(cliente);
	}

	private void validarCliente(Cliente cliente) {
		// 
	}
	
}
