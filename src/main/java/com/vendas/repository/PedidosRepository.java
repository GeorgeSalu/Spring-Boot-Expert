package com.vendas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendas.entity.Pedido;

public interface PedidosRepository extends JpaRepository<Pedido,Integer>{

	Optional<Pedido> findByIdFetchItens(Integer id);
}
