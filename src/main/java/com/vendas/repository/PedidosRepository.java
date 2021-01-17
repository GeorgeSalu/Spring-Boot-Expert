package com.vendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendas.entity.Pedido;

public interface PedidosRepository extends JpaRepository<Pedido,Integer>{

}
