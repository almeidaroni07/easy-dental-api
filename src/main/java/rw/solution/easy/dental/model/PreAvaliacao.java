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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.solution.easy.dental.model.record.DadosPreAvaliacao;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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
	
	public PreAvaliacao(DadosPreAvaliacao dados, Paciente paciente) {
		this.paciente = paciente;
		this.corDentesIncomoda = dados.corDentesIncomoda();
		this.formatoDentesIncomoda = dados.formatoDentesIncomoda();
		this.fumante = dados.fumante();
		this.umAnoTratamento = dados.umAnoTratamento();
		this.escovaDuasVezesDia = dados.escovaDuasVezesDia();
		this.usaFioDental = dados.usaFioDental();
		this.queixa = dados.queixa();
	}

	public void atualizaInformacoes(DadosPreAvaliacao dados) {
		this.corDentesIncomoda = dados.corDentesIncomoda();
		this.formatoDentesIncomoda = dados.formatoDentesIncomoda();
		this.fumante = dados.fumante();
		this.umAnoTratamento = dados.umAnoTratamento();
		this.escovaDuasVezesDia = dados.escovaDuasVezesDia();
		this.usaFioDental = dados.usaFioDental();
		this.queixa = dados.queixa();
	}

	
}
