package rw.solution.easy.dental.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "arquivo")
public class Arquivo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1396123409477002346L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	@JsonIgnore
	private Customer customer;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "tipo_arquivo")
	private String tipo;
	
	@JsonIgnore
	@Lob
	@Column(name = "bl_arquivo", nullable = true)
	private byte[] arquivo;
	
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
	public byte[] getArquivo() {
		return arquivo;
	}
	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(arquivo);
		result = prime * result + Objects.hash(id, nome);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arquivo other = (Arquivo) obj;
		return Arrays.equals(arquivo, other.arquivo) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome);
	}
	@Override
	public String toString() {
		return "Arquivo [id=" + id + ", nome=" + nome + ", arquivo=" + Arrays.toString(arquivo) + "]";
	}
}
