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
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.solution.easy.dental.model.record.DadosAnamnese;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "anamnese")
public class Anamnese implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5654676295058496479L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paciente_id", referencedColumnName = "id")
	@JsonIgnore
	private Paciente paciente;
	
	
	@Column(name = "alergico_medicamento")
	private boolean alergicoMedicamento;
	@Column(name = "alergico_medicamento_qual")
	private String alergicoMedicamentoQual;
	@Column(name = "usa_medicamento")
	private boolean usaMedicamento;
	@Column(name = "usa_medicamento_qual")
	private String usaMedicamentoQual;
	@Column(name = "alergia_anestesia")
	private boolean alergiaAnestesia;
	@Column(name = "alergia_anestesia_qual")
	private String alergiaAnestesiaQual;
	@Column(name = "gravida_amamentando")
	private boolean gravidaOuAmamentando;
	@Column(name = "doenca_cardio_respiratoria")
	private boolean doencaCardioRespiratoria;
	@Column(name = "doenca_cardio_respiratoria_qual")
	private String doencaCardioRespiratoriaQual;
	@Column(name = "doenca_transmissivel")
	private boolean doencaoTransmissivel;
	@Column(name = "doenca_transmissivel_qual")
	private String doencaoTransmissivelQual;
	@Column(name = "diabetico")
	private boolean diabetico;
	@Column(name = "hipertencao_arterial")
	private boolean hipertencaoArterial;
	@Column(name = "hemorragico")
	private boolean hemorragico;
	@Column(name = "cirurgia")
	private boolean cirurgia;
	@Column(name = "cirurgia_qual")
	private String cirurgiaQual;
	@Column(name = "informacaoAdicional")
	private String informacaoAdicional;
	
	
	public Anamnese(DadosAnamnese dados, Paciente paciente) {
		this.paciente = paciente;
		this.alergicoMedicamento = dados.alergicoMedicamento();
		this.alergicoMedicamentoQual = dados.alergicoMedicamentoQual();
		this.usaMedicamento = dados.usaMedicamento();
		this.usaMedicamentoQual = dados.usaMedicamentoQual();
		this.alergiaAnestesia = dados.alergiaAnestesia();
		this.alergiaAnestesiaQual = dados.alergiaAnestesiaQual();
		this.gravidaOuAmamentando = dados.gravidaOuAmamentando();
		this.doencaCardioRespiratoria = dados.doencaCardioRespiratoria();
		this.doencaCardioRespiratoriaQual = dados.doencaCardioRespiratoriaQual();	
		this.doencaoTransmissivel = dados.doencaoTransmissivel();
		this.doencaoTransmissivelQual = dados.doencaoTransmissivelQual();
		this.diabetico = dados.diabetico();
		this.hipertencaoArterial = dados.hipertencaoArterial();
		this.hemorragico = dados.hemorragico();
		this.cirurgia = dados.cirurgia();
		this.cirurgiaQual = dados.cirurgiaQual();
		this.informacaoAdicional = dados.informacaoAdicional();
	}
	
	public void atualizarInformacoes(@Valid DadosAnamnese dados) {
		this.alergicoMedicamento = dados.alergicoMedicamento();
		this.alergicoMedicamentoQual = dados.alergicoMedicamentoQual();
		this.usaMedicamento = dados.usaMedicamento();
		this.usaMedicamentoQual = dados.usaMedicamentoQual();
		this.alergiaAnestesia = dados.alergiaAnestesia();
		this.alergiaAnestesiaQual = dados.alergiaAnestesiaQual();
		this.gravidaOuAmamentando = dados.gravidaOuAmamentando();
		this.doencaCardioRespiratoria = dados.doencaCardioRespiratoria();
		this.doencaCardioRespiratoriaQual = dados.doencaCardioRespiratoriaQual();	
		this.doencaoTransmissivel = dados.doencaoTransmissivel();
		this.doencaoTransmissivelQual = dados.doencaoTransmissivelQual();
		this.diabetico = dados.diabetico();
		this.hipertencaoArterial = dados.hipertencaoArterial();
		this.hemorragico = dados.hemorragico();
		this.cirurgia = dados.cirurgia();
		this.cirurgiaQual = dados.cirurgiaQual();
		this.informacaoAdicional = dados.informacaoAdicional();
	}
	
}
