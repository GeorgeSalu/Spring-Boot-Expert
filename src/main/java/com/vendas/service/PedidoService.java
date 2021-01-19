package com.vendas.service;

import com.vendas.dto.PedidoDTO;
import com.vendas.entity.Pedido;

public interface PedidoService {
	Pedido salvar(PedidoDTO dto);
}
