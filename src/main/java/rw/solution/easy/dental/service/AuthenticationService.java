package rw.solution.easy.dental.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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

	public boolean isTokenValid(String token) throws Exception {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getUserID(String token) throws Exception {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

	public User getUserAuthByUserID(Long userID) throws Exception {
		return this.repository.getUserAuthByUserID(userID);
	}

	public String createToken(Authentication authentication) throws Exception {
		User agentAuth = (User) authentication.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder().setIssuer(agentAuth.getNome()).setSubject(agentAuth.getId().toString()).setIssuedAt(hoje)
				.setExpiration(dataExpiracao).signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public Long getCustomersByUsername(String username) throws Exception {
		User user = this.repository.getUserByUsername(username);
		return user.getCustomer().getId();
	}

}
