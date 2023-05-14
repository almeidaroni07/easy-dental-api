package rw.solution.easy.dental.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import rw.solution.easy.dental.model.repository.AuthenticationRepository;
import rw.solution.easy.dental.security.model.User;

@Service
public class AuthenticationService {

	@Value("${forum.jwt.expiration}")
	private String expiration;

	@Value("${forum.jwt.secret}")
	private String secret;

	@Autowired
	private AuthenticationRepository repository;

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

	
	public String createToken(Authentication authentication) {
		try {
			
			User usuario = (User) authentication.getPrincipal();
			
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
	
	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

	public User getUserAuthByUserID(Long userID) throws Exception {
		return this.repository.getUserAuthByUserID(userID);
	}
	
	public Long getCustomersByUsername(String username) throws Exception {
		User user = this.repository.getUserByUsername(username);
		return user.getCustomer().getId();
	}


	public User getUsuarioByUsername(String subject) throws Exception  {
		return this.repository.getUserByUsername(subject);
	}

}
