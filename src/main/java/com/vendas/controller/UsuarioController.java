package com.vendas.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vendas.entity.Usuario;
import com.vendas.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	private UsuarioServiceImpl usuarioService;
	private PasswordEncoder passwordEncoder;

	public UsuarioController(UsuarioServiceImpl usuarioService, PasswordEncoder passwordEncoder) {
		this.usuarioService = usuarioService;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Usuario salvar(@RequestBody @Valid Usuario usuario) {
		String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		return usuarioService.salvar(usuario);
	}
	
}
