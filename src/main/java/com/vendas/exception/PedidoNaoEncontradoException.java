package com.vendas.exception;

public class PedidoNaoEncontradoException extends RuntimeException{
	public PedidoNaoEncontradoException() {
		super("PEdido não encontrado");
	}
}
