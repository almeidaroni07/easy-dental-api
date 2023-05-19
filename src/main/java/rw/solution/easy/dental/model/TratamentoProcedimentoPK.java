package rw.solution.easy.dental.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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
	
}
