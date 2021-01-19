package com.vendas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vendas.exception.PedidoNaoEncontradoException;
import com.vendas.exception.RegraNegocioException;
import com.vendas.rest.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(RegraNegocioException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleRegraNegocioException(RegraNegocioException ex) {
		String mensagemErro = ex.getMessage();
		return new ApiErrors(mensagemErro);
	}
	
	@ExceptionHandler(PedidoNaoEncontradoException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ApiErrors handlePedidoNotDoundException(PedidoNaoEncontradoException exception) {
		return new ApiErrors(exception.getMessage());
	}
}
