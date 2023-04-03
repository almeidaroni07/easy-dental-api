package rw.solution.easy.dental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rw.solution.easy.dental.security.model.AuthenticationUser;
import rw.solution.easy.dental.security.model.UserAuth;
import rw.solution.easy.dental.service.AuthenticationService;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping(value="/login")
	public ResponseEntity<AuthenticationUser> loginUserWS(@RequestBody(required = true) UserAuth auth) {
		try {
			
			UsernamePasswordAuthenticationToken loginDATA = auth.getAuth();
			Authentication authentication = authManager.authenticate(loginDATA);
			
			String token = this.authenticationService.createToken(authentication);
			
			Long customer = this.authenticationService.getCustomersByUsername(auth.getUsername());
			
			return ResponseEntity.ok(new AuthenticationUser(token, "Bearer ", customer));
			
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	

}
