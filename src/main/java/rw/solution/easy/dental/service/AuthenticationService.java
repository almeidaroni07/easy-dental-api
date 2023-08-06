package rw.solution.easy.dental.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import rw.solution.easy.dental.model.User;
import rw.solution.easy.dental.model.UserStatus;
import rw.solution.easy.dental.model.repository.UsuarioRepository;

@Service
public class AuthenticationService {

	@Value("${forum.jwt.secret}")
	private String secret = "";
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public String gerarToken(User usuario) {
		try {
		    return JWT.create()
		        .withIssuer("API RW Solutions")
		        .withSubject(usuario.getUsername())
		        .withExpiresAt(dataExpiracao())
		        .withClaim("id", usuario.getId())
		        .withClaim("nome", usuario.getNome())
		        .sign(Algorithm.HMAC256(secret));
		} catch (JWTCreationException exception){
		    throw new RuntimeException("Erro ao gerar o token", exception);
		}
	}

	
	public String getSubject(String token) {
		
		try {
		    return JWT.require(Algorithm.HMAC256(secret))
		        .withIssuer("API RW Solutions")
		        .build()
		        .verify(token)
		        .getSubject();
		        
		} catch (JWTVerificationException exception){
		   throw new RuntimeException("Token Invalido");
		}
	}
	
	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

	public User getUsuarioByUsername(String username) {
		return this.usuarioRepository.getUsuarioByUsername(username, UserStatus.ATIVO);
	}

}
