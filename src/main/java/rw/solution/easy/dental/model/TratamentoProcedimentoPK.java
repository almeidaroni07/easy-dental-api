package rw.solution.easy.dental.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TratamentoProcedimentoPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -969138138570304557L;
	
	@Column(name = "tratamento_id")
	private Long tratamentoID;
	@Column(name = "procedimento_id")
	private Long procedimentoID;
	
	public TratamentoProcedimentoPK() {
		// TODO Auto-generated constructor stub
	}
	
	public TratamentoProcedimentoPK(Long tratamentoID, Long procedimentoID) {
		super();
		this.tratamentoID = tratamentoID;
		this.procedimentoID = procedimentoID;
	}
	public Long getTratamentoID() {
		return tratamentoID;
	}
	public void setTratamentoID(Long tratamentoID) {
		this.tratamentoID = tratamentoID;
	}
	public Long getProcedimentoID() {
		return procedimentoID;
	}
	public void setProcedimentoID(Long procedimentoID) {
		this.procedimentoID = procedimentoID;
	}
}
