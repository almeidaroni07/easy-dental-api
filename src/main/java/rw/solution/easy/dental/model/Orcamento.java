package rw.solution.easy.dental.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

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
import jakarta.persistence.Transient;
import rw.solution.easy.dental.model.convert.LocalDateConverter;

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
	
	
	@Transient
	private String dataFmt;
	
	public Orcamento() {
		// TODO Auto-generated constructor stub
	}

	public Orcamento(Long pacienteID, LocalDate data, Double valorTotal) {
		super();
		this.pacienteID = pacienteID;
		this.data = data;
		this.valorTotal = valorTotal;
	}
	
	public Orcamento(Long pacienteID, LocalDate data, Customer customer, String pacienteNome) {
		super();
		this.pacienteID = pacienteID;
		this.data = data;
		this.customer = customer;
		this.pacienteNome = pacienteNome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPacienteID() {
		return pacienteID;
	}

	public void setPacienteID(Long pacienteID) {
		this.pacienteID = pacienteID;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<OrcamentoProcedimento> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<OrcamentoProcedimento> procedimentos) {
		this.procedimentos = procedimentos;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getPacienteNome() {
		return pacienteNome;
	}

	public void setPacienteNome(String pacienteNome) {
		this.pacienteNome = pacienteNome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(data, id, pacienteID, procedimentos, valorTotal);
	}
	
	

	public String getDataFmt() {
		try {
			return this.data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dataFmt;
	}

	public void setDataFmt(String dataFmt) {
		this.dataFmt = dataFmt;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orcamento other = (Orcamento) obj;
		return Objects.equals(data, other.data) && Objects.equals(id, other.id)
				&& Objects.equals(pacienteID, other.pacienteID) && Objects.equals(procedimentos, other.procedimentos)
				&& Objects.equals(valorTotal, other.valorTotal);
	}

	@Override
	public String toString() {
		return "Orcamento [id=" + id + ", pacienteID=" + pacienteID + ", data=" + data + ", valorTotal=" + valorTotal
				+ ", procedimentos=" + procedimentos + "]";
	}

}
