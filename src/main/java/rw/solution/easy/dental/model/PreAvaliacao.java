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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pre_avaliacao")
public class PreAvaliacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3269204451219506667L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paciente_id", referencedColumnName = "id")
	@JsonIgnore
	private Paciente paciente;
	
	@Column(name = "cor_dentes_incomoda")
	private boolean corDentesIncomoda;
	@Column(name = "formato_dentes_incomoda")
	private boolean formatoDentesIncomoda;
	@Column(name = "fumante")
	private boolean fumante;
	@Column(name = "um_ano_tratamento")
	private boolean umAnoTratamento;
	@Column(name = "escova_duas_vezes_dia")
	private boolean escovaDuasVezesDia;
	@Column(name = "fio_dental")
	private boolean usaFioDental;
	@Column(name = "queixa")
	private String queixa;
	
	
	public PreAvaliacao() {
		// TODO Auto-generated constructor stub
	}
	
	public PreAvaliacao(Paciente paciente, boolean corDentesIncomoda, boolean formatoDentesIncomoda,
			boolean fumante, boolean umAnoTratamento, boolean escovaDuasVezesDia, boolean usaFioDental, String queixa) {
		super();
		this.paciente = paciente;
		this.corDentesIncomoda = corDentesIncomoda;
		this.formatoDentesIncomoda = formatoDentesIncomoda;
		this.fumante = fumante;
		this.umAnoTratamento = umAnoTratamento;
		this.escovaDuasVezesDia = escovaDuasVezesDia;
		this.usaFioDental = usaFioDental;
		this.queixa = queixa;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public boolean isCorDentesIncomoda() {
		return corDentesIncomoda;
	}
	public void setCorDentesIncomoda(boolean corDentesIncomoda) {
		this.corDentesIncomoda = corDentesIncomoda;
	}
	public boolean isFormatoDentesIncomoda() {
		return formatoDentesIncomoda;
	}
	public void setFormatoDentesIncomoda(boolean formatoDentesIncomoda) {
		this.formatoDentesIncomoda = formatoDentesIncomoda;
	}
	public boolean isFumante() {
		return fumante;
	}
	public void setFumante(boolean fumante) {
		this.fumante = fumante;
	}
	public boolean isUmAnoTratamento() {
		return umAnoTratamento;
	}
	public void setUmAnoTratamento(boolean umAnoTratamento) {
		this.umAnoTratamento = umAnoTratamento;
	}
	public boolean isEscovaDuasVezesDia() {
		return escovaDuasVezesDia;
	}
	public void setEscovaDuasVezesDia(boolean escovaDuasVezesDia) {
		this.escovaDuasVezesDia = escovaDuasVezesDia;
	}
	public boolean isUsaFioDental() {
		return usaFioDental;
	}
	public void setUsaFioDental(boolean usaFioDental) {
		this.usaFioDental = usaFioDental;
	}
	public String getQueixa() {
		return queixa;
	}
	public void setQueixa(String queixa) {
		this.queixa = queixa;
	}
	@Override
	public int hashCode() {
		return Objects.hash(corDentesIncomoda, escovaDuasVezesDia, formatoDentesIncomoda, fumante, id, paciente, queixa,
				umAnoTratamento, usaFioDental);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PreAvaliacao other = (PreAvaliacao) obj;
		return corDentesIncomoda == other.corDentesIncomoda && escovaDuasVezesDia == other.escovaDuasVezesDia
				&& formatoDentesIncomoda == other.formatoDentesIncomoda && fumante == other.fumante
				&& Objects.equals(id, other.id) && Objects.equals(paciente, other.paciente)
				&& Objects.equals(queixa, other.queixa) && umAnoTratamento == other.umAnoTratamento
				&& usaFioDental == other.usaFioDental;
	}
	@Override
	public String toString() {
		return "PreAvaliacao [id=" + id + ", paciente=" + paciente + ", corDentesIncomoda=" + corDentesIncomoda
				+ ", formatoDentesIncomoda=" + formatoDentesIncomoda + ", fumante=" + fumante + ", umAnoTratamento="
				+ umAnoTratamento + ", escovaDuasVezesDia=" + escovaDuasVezesDia + ", usaFioDental=" + usaFioDental
				+ ", queixa=" + queixa + "]";
	}
	
}
