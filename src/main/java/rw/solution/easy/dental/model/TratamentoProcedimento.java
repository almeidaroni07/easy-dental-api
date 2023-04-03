package rw.solution.easy.dental.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tratamento_procedimento")
public class TratamentoProcedimento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1528421457930521449L;
	
	@EmbeddedId
	private TratamentoProcedimentoPK pk;
	
	@Column(name = "nome")
	private String nome;
	
	public TratamentoProcedimento() {
		// TODO Auto-generated constructor stub
	}

	public TratamentoProcedimento(TratamentoProcedimentoPK pk, String nome) {
		super();
		this.pk = pk;
		this.nome = nome;
	}

	public TratamentoProcedimentoPK getPk() {
		return pk;
	}

	public void setPk(TratamentoProcedimentoPK pk) {
		this.pk = pk;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
