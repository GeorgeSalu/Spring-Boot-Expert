package com.vendas.service.impl;

import org.springframework.stereotype.Service;

import com.vendas.repository.PedidosRepository;
import com.vendas.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

	private PedidosRepository pedidosRepository;

	public PedidoServiceImpl(PedidosRepository pedidosRepository) {
		this.pedidosRepository = pedidosRepository;
	}
	
}
