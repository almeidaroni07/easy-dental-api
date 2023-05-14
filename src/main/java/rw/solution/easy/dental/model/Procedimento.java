package rw.solution.easy.dental.model;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "procedimento")
public class Procedimento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5752092312691132462L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	@JsonIgnore
	private Customer customer;
	
	@Column(name = "nome")
	private String nome;
	@Column(name = "valor")
	private Double valor;
	@Column(name = "cor")
	private String cor;
	
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
	
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	@Override
	public int hashCode() {
		return Objects.hash(customer, id, nome, valor);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Procedimento other = (Procedimento) obj;
		return Objects.equals(customer, other.customer) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome)
				&& Double.doubleToLongBits(valor) == Double.doubleToLongBits(other.valor);
	}
	@Override
	public String toString() {
		return "Procedimento [id=" + id + ", customer=" + customer + ", nome=" + nome + ", valor=" + valor + "]";
	}
	
	
	
	
}
