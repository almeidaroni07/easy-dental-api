package rw.solution.easy.dental.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.solution.easy.dental.model.convert.LocalDateConverter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "orcamento")
public class Orcamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3790616136683044558L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	@JsonIgnore
	private Customer customer;
	
	@Column(name = "paciente_id")
	private Long pacienteID;
	
	@Column(name = "paciente_nome")
	private String pacienteNome;
	
	
	@Column(name = "data")
	@Convert(converter = LocalDateConverter.class)
	private LocalDate data;
	
	@Column(name = "valor_total")
	private Double valorTotal;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "tratamento_id", referencedColumnName = "id")
    private List<OrcamentoProcedimento> procedimentos;
	
	
	public Orcamento(Customer customer, Paciente paciente ) {
		this.customer = customer;
		this.pacienteID = paciente.getId();
		this.pacienteNome = paciente.getNome();
		this.data = LocalDate.now();
	}
	
	
	public String getDataFmt() {
		try {
			return this.data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}

}
