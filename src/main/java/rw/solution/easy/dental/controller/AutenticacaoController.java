package rw.solution.easy.dental.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import rw.solution.easy.dental.model.User;
import rw.solution.easy.dental.model.dto.DadosLogin;
import rw.solution.easy.dental.model.dto.DadosTokenJWT;
import rw.solution.easy.dental.service.AuthenticationService;

@CrossOrigin
@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {
	
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping(value="/login")
	public ResponseEntity<DadosTokenJWT> loginUserWS(@RequestBody(required = true) @Valid DadosLogin login) {
	
		UsernamePasswordAuthenticationToken loginDATA = new UsernamePasswordAuthenticationToken(login.username(), login.password());
		Authentication authentication = authManager.authenticate(loginDATA);
		
		String token = this.authenticationService.gerarToken((User) authentication.getPrincipal());
		
		List<Long> idsClinicas = new ArrayList<Long>();
		
		return ResponseEntity.ok(new DadosTokenJWT(token, "Bearer ", idsClinicas));
		
	}
	

}
