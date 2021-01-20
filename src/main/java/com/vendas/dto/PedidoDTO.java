package com.vendas.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

public class PedidoDTO {
	
	@NotNull(message = "informe o codigo do cliente")
	private Integer cliente;
	@NotNull(message = "campo total do pedido é obrigatorio")
	private BigDecimal total;
	private List<ItemPedidoDTO> items;

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<ItemPedidoDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemPedidoDTO> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "PedidoDTO [cliente=" + cliente + ", total=" + total + ", items=" + items + "]";
	}

}
