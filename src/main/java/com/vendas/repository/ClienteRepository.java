package com.vendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vendas.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	@Query(value = "select c from Cliente c where c.nome like :nome")
	List<Cliente> encontrarPorNome(@Param("nome") String nome);

	List<Cliente> findByNomeLike(String nome);
	
	List<Cliente> findByNomeOrId(String nome, Integer id);
	
	boolean existsByNome(String nome);
}
