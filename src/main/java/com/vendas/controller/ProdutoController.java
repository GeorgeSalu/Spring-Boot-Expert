package com.vendas.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.vendas.entity.Cliente;
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
	public Produto save(@RequestBody @Valid Produto produto) {
		return produtosRepository.save(produto);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id,
						@RequestBody @Valid Produto produto) {
		produtosRepository.findById(id)
			.map(p -> {
				produto.setId(p.getId());
				produtosRepository.save(produto);
				return produto;
			}).orElseThrow(() -> 
				new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado")
			);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		produtosRepository.findById(id)
			.map(p -> {
				produtosRepository.delete(p);
				return Void.TYPE;
			}).orElseThrow(() -> 
				new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado")
			);
	}
	
	@GetMapping("{id}")
	public Produto getById(@PathVariable Integer id) {
		return produtosRepository.findById(id)
				.orElseThrow(() -> 
				new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado")
			);
	}
	
	@GetMapping
	public List<Produto> find(Produto filtro) {
		ExampleMatcher matcher = ExampleMatcher
									.matching()
									.withIgnoreCase()
									.withStringMatcher(StringMatcher.CONTAINING);
		Example example = Example.of(filtro, matcher);
		return produtosRepository.findAll(example);
	}
}
