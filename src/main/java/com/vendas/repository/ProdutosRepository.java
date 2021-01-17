package com.vendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendas.entity.Produto;

public interface ProdutosRepository extends JpaRepository<Produto, Integer> {

}
