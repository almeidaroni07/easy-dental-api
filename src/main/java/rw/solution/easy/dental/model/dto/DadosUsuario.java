package rw.solution.easy.dental.model.dto;

import rw.solution.easy.dental.model.User;

public record DadosUsuario(String nome, String username, String password) {
	
	public DadosUsuario(User usuario) {
		this(usuario.getNome(), usuario.getUsername(), "");
	}

}
