package rw.solution.easy.dental.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "orcamento_procedimento")
public class OrcamentoProcedimento implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -315061885245077813L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orcamento_id", referencedColumnName = "id")
	private Orcamento orcamento;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "valor")
	private Double valor;
	
	public OrcamentoProcedimento() {
		// TODO Auto-generated constructor stub
	}
	
	public OrcamentoProcedimento(Orcamento orcamento, String nome, Double valor) {
		super();
		this.orcamento = orcamento;
		this.nome = nome;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
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

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, orcamento, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrcamentoProcedimento other = (OrcamentoProcedimento) obj;
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(orcamento, other.orcamento) && Objects.equals(valor, other.valor);
	}

	@Override
	public String toString() {
		return "OrcamentoProcedimento [id=" + id + ", orcamento=" + orcamento + ", nome=" + nome + ", valor=" + valor
				+ "]";
	}

}
