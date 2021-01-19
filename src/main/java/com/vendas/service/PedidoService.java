package com.vendas.service;

import java.util.Optional;

import com.vendas.dto.PedidoDTO;
import com.vendas.entity.Pedido;
import com.vendas.enums.StatusPedido;

public interface PedidoService {
	Pedido salvar(PedidoDTO dto);
	
	Optional<Pedido> obterPedidoCompleto(Integer id);

	void atualizaStatus(Integer id, StatusPedido statusPedido);
}
