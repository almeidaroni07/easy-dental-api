package rw.solution.easy.dental.security.model;

import java.io.Serializable;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserAuth implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6865762400510843701L;
	
	private String username;
	private String password;

	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@JsonIgnore
	public UsernamePasswordAuthenticationToken getAuth() {
		return new UsernamePasswordAuthenticationToken(this.username, this.password);
	}

}
