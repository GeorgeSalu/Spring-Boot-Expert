package com.vendas.rest;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {
	private List<String> erros;

	public ApiErrors(String mensagemErro) {
		this.erros = Arrays.asList(mensagemErro);
	}

	public List<String> getErros() {
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}

}
