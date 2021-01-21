package com.vendas;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vendas.entity.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	@Value("${security.jwt.expiracao}")
	private String expiracao;
	@Value("${security.jwt.chave-assinatura}")
	private String chaveAssinatura;
	
	public String gerarToken(Usuario usuario) {
		long expString = Long.valueOf(expiracao);
		LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
		Date date = Date.from(dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant());
		return Jwts
				.builder()
				.setSubject(usuario.getLogin())
				.setExpiration(date)
				.signWith(SignatureAlgorithm.HS512, chaveAssinatura)
				.compact();
	}
	
}
