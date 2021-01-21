package com.vendas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vendas.entity.Usuario;
import com.vendas.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder encoder;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Usuario usuario = usuarioRepository.findByLogin(username)
					.orElseThrow(() -> new UsernameNotFoundException("usuario não encontrado na base de dados"));
		 
		 String[] roles = usuario.isAdmin() ? 
				 new String[] {"ADMIN", "USER"} : new String[] {"USER"};
		 
		 return User
				 .builder()
				 .username(usuario.getLogin())
				 .password(usuario.getSenha())
				 .roles(roles)
				 .build();
	}

}
