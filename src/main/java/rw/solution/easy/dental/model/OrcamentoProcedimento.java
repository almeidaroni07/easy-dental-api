package rw.solution.easy.dental.model;

import java.io.Serializable;

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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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
	
	public OrcamentoProcedimento(Orcamento orcamento, Procedimento procedimento) {
		this.orcamento = orcamento;
		this.nome = procedimento.getNome();
		this.valor = procedimento.getValor();
	}

}
