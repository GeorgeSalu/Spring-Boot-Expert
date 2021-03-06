package com.vendas.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vendas.exception.PedidoNaoEncontradoException;
import com.vendas.exception.RegraNegocioException;
import com.vendas.rest.ApiErrors;

@RestControllerAdvice
public class ApplicationHandleException {

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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getAllErrors()
			.stream()
			.map(erro -> erro.getDefaultMessage())
			.collect(Collectors.toList());
		return new ApiErrors(errors);
	}
}
