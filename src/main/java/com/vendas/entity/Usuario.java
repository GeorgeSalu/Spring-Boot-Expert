package com.vendas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	@NotEmpty(message = "{campo.login.obrigatorio}")
	private String login;
	
	@Column
	@NotEmpty(message = "{campo.senha.obrigatorio}")
	private String senha;
	
	@Column
	private boolean admin;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(Integer id, String login, String senha, boolean admin) {
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.admin = admin;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + login + ", senha=" + senha + ", admin=" + admin + "]";
	}
	
	
}
