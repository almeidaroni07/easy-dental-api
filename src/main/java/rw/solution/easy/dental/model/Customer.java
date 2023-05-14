package rw.solution.easy.dental.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import rw.solution.easy.dental.model.enums.StatusCustomer;

@Entity
@Table(name = "customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private StatusCustomer status;
	@Lob
	@Column(name = "bl_logo", nullable = true)
	private byte[] logo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public StatusCustomer getStatus() {
		return status;
	}
	public void setStatus(StatusCustomer status) {
		this.status = status;
	}
	public byte[] getLogo() {
		return logo;
	}
	public void setLogo(byte[] logo) {
		this.logo = logo;
	}	
}
