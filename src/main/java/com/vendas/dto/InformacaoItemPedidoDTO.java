package com.vendas.dto;

public class InformacaoItemPedidoDTO {
	private String descricaoProduto;
	private String precoUnitario;
	private Integer quantidade;

	public InformacaoItemPedidoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public InformacaoItemPedidoDTO(String descricaoProduto, String precoUnitario, Integer quantidade) {
		super();
		this.descricaoProduto = descricaoProduto;
		this.precoUnitario = precoUnitario;
		this.quantidade = quantidade;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public String getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(String precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
