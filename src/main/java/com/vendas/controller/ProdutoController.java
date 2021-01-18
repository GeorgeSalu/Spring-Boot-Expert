package com.vendas.controller;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.vendas.entity.Produto;
import com.vendas.repository.ProdutosRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	private ProdutosRepository produtosRepository;

	public ProdutoController(ProdutosRepository produtosRepository) {
		this.produtosRepository = produtosRepository;
	}
	
	
	@PostMapping
	@ResponseStatus(value = CREATED)
	public Produto save(@RequestBody Produto produto) {
		return produtosRepository.save(produto);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id,
						@RequestBody Produto produto) {
		produtosRepository.findById(id)
			.map(p -> {
				produto.setId(p.getId());
				produtosRepository.save(produto);
				return produto;
			}).orElseThrow(() -> 
				new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado")
			);
	}
}
