package com.vendas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendas.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Optional<Usuario> findByLogin(String isbn);
	
}
