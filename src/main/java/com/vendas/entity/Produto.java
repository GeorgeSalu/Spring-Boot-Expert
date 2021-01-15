package com.vendas.entity;

import java.math.BigDecimal;

public class Produto {
	private Integer id;
	private String descrica;
	private BigDecimal preco;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescrica() {
		return descrica;
	}

	public void setDescrica(String descrica) {
		this.descrica = descrica;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

}
