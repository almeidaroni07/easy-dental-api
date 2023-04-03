package rw.solution.easy.dental.security.model;

import java.io.Serializable;

public class AuthenticationUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2212778420560800676L;
	
	private String token;
	private String tipo;
	private Long customerID;
	
	public AuthenticationUser() {
		// TODO Auto-generated constructor stub
	}

	public AuthenticationUser(String token, String tipo, Long customerID) {
		super();
		this.token = token;
		this.tipo = tipo;
		this.customerID = customerID;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}
	
	
}
