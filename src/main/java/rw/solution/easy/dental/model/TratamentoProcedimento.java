package rw.solution.easy.dental.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
@EqualsAndHashCode(of = "pk")
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
	
}
