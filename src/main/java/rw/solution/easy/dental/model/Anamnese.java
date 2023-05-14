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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;



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
	
	public Anamnese() {
		
	}
	
	public Anamnese(Long id, Paciente paciente, boolean alergicoMedicamento, String alergicoMedicamentoQual,
			boolean usaMedicamento, String usaMedicamentoQual, boolean alergiaAnestesia, String alergiaAnestesiaQual,
			boolean gravidaOuAmamentando, boolean doencaCardioRespiratoria, String doencaCardioRespiratoriaQual,
			boolean doencaoTransmissivel, String doencaoTransmissivelQual, boolean diabetico,
			boolean hipertencaoArterial, boolean hemorragico, boolean cirurgia, String cirurgiaQual,
			String informacaoAdicional) {
		super();
		this.id = id;
		this.paciente = paciente;
		this.alergicoMedicamento = alergicoMedicamento;
		this.alergicoMedicamentoQual = alergicoMedicamentoQual;
		this.usaMedicamento = usaMedicamento;
		this.usaMedicamentoQual = usaMedicamentoQual;
		this.alergiaAnestesia = alergiaAnestesia;
		this.alergiaAnestesiaQual = alergiaAnestesiaQual;
		this.gravidaOuAmamentando = gravidaOuAmamentando;
		this.doencaCardioRespiratoria = doencaCardioRespiratoria;
		this.doencaCardioRespiratoriaQual = doencaCardioRespiratoriaQual;
		this.doencaoTransmissivel = doencaoTransmissivel;
		this.doencaoTransmissivelQual = doencaoTransmissivelQual;
		this.diabetico = diabetico;
		this.hipertencaoArterial = hipertencaoArterial;
		this.hemorragico = hemorragico;
		this.cirurgia = cirurgia;
		this.cirurgiaQual = cirurgiaQual;
		this.informacaoAdicional = informacaoAdicional;
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
	public boolean isAlergicoMedicamento() {
		return alergicoMedicamento;
	}
	public void setAlergicoMedicamento(boolean alergicoMedicamento) {
		this.alergicoMedicamento = alergicoMedicamento;
	}
	public String getAlergicoMedicamentoQual() {
		return alergicoMedicamentoQual;
	}
	public void setAlergicoMedicamentoQual(String alergicoMedicamentoQual) {
		this.alergicoMedicamentoQual = alergicoMedicamentoQual;
	}
	public boolean isUsaMedicamento() {
		return usaMedicamento;
	}
	public void setUsaMedicamento(boolean usaMedicamento) {
		this.usaMedicamento = usaMedicamento;
	}
	public String getUsaMedicamentoQual() {
		return usaMedicamentoQual;
	}
	public void setUsaMedicamentoQual(String usaMedicamentoQual) {
		this.usaMedicamentoQual = usaMedicamentoQual;
	}
	public boolean isAlergiaAnestesia() {
		return alergiaAnestesia;
	}
	public void setAlergiaAnestesia(boolean alergiaAnestesia) {
		this.alergiaAnestesia = alergiaAnestesia;
	}
	public String getAlergiaAnestesiaQual() {
		return alergiaAnestesiaQual;
	}
	public void setAlergiaAnestesiaQual(String alergiaAnestesiaQual) {
		this.alergiaAnestesiaQual = alergiaAnestesiaQual;
	}
	public boolean isGravidaOuAmamentando() {
		return gravidaOuAmamentando;
	}
	public void setGravidaOuAmamentando(boolean gravidaOuAmamentando) {
		this.gravidaOuAmamentando = gravidaOuAmamentando;
	}
	public boolean isDoencaCardioRespiratoria() {
		return doencaCardioRespiratoria;
	}
	public void setDoencaCardioRespiratoria(boolean doencaCardioRespiratoria) {
		this.doencaCardioRespiratoria = doencaCardioRespiratoria;
	}
	public String getDoencaCardioRespiratoriaQual() {
		return doencaCardioRespiratoriaQual;
	}
	public void setDoencaCardioRespiratoriaQual(String doencaCardioRespiratoriaQual) {
		this.doencaCardioRespiratoriaQual = doencaCardioRespiratoriaQual;
	}
	public boolean isDoencaoTransmissivel() {
		return doencaoTransmissivel;
	}
	public void setDoencaoTransmissivel(boolean doencaoTransmissivel) {
		this.doencaoTransmissivel = doencaoTransmissivel;
	}
	public String getDoencaoTransmissivelQual() {
		return doencaoTransmissivelQual;
	}
	public void setDoencaoTransmissivelQual(String doencaoTransmissivelQual) {
		this.doencaoTransmissivelQual = doencaoTransmissivelQual;
	}
	public boolean isDiabetico() {
		return diabetico;
	}
	public void setDiabetico(boolean diabetico) {
		this.diabetico = diabetico;
	}
	public boolean isHipertencaoArterial() {
		return hipertencaoArterial;
	}
	public void setHipertencaoArterial(boolean hipertencaoArterial) {
		this.hipertencaoArterial = hipertencaoArterial;
	}
	public boolean isHemorragico() {
		return hemorragico;
	}
	public void setHemorragico(boolean hemorragico) {
		this.hemorragico = hemorragico;
	}
	public boolean isCirurgia() {
		return cirurgia;
	}
	public void setCirurgia(boolean cirurgia) {
		this.cirurgia = cirurgia;
	}
	
	public String getCirurgiaQual() {
		return cirurgiaQual;
	}

	public void setCirurgiaQual(String cirurgiaQual) {
		this.cirurgiaQual = cirurgiaQual;
	}

	public String getInformacaoAdicional() {
		return informacaoAdicional;
	}
	public void setInformacaoAdicional(String informacaoAdicional) {
		this.informacaoAdicional = informacaoAdicional;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alergiaAnestesia, alergiaAnestesiaQual, alergicoMedicamento, alergicoMedicamentoQual,
				cirurgia, cirurgiaQual, diabetico, doencaCardioRespiratoria, doencaCardioRespiratoriaQual,
				doencaoTransmissivel, doencaoTransmissivelQual, gravidaOuAmamentando, hemorragico, hipertencaoArterial,
				id, informacaoAdicional, paciente, usaMedicamento, usaMedicamentoQual);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Anamnese other = (Anamnese) obj;
		return alergiaAnestesia == other.alergiaAnestesia
				&& Objects.equals(alergiaAnestesiaQual, other.alergiaAnestesiaQual)
				&& alergicoMedicamento == other.alergicoMedicamento
				&& Objects.equals(alergicoMedicamentoQual, other.alergicoMedicamentoQual) && cirurgia == other.cirurgia
				&& Objects.equals(cirurgiaQual, other.cirurgiaQual) && diabetico == other.diabetico
				&& doencaCardioRespiratoria == other.doencaCardioRespiratoria
				&& Objects.equals(doencaCardioRespiratoriaQual, other.doencaCardioRespiratoriaQual)
				&& doencaoTransmissivel == other.doencaoTransmissivel
				&& Objects.equals(doencaoTransmissivelQual, other.doencaoTransmissivelQual)
				&& gravidaOuAmamentando == other.gravidaOuAmamentando && hemorragico == other.hemorragico
				&& hipertencaoArterial == other.hipertencaoArterial && Objects.equals(id, other.id)
				&& Objects.equals(informacaoAdicional, other.informacaoAdicional)
				&& Objects.equals(paciente, other.paciente) && usaMedicamento == other.usaMedicamento
				&& Objects.equals(usaMedicamentoQual, other.usaMedicamentoQual);
	}

	@Override
	public String toString() {
		return "Anamnese [id=" + id + ", paciente=" + paciente + ", alergicoMedicamento=" + alergicoMedicamento
				+ ", alergicoMedicamentoQual=" + alergicoMedicamentoQual + ", usaMedicamento=" + usaMedicamento
				+ ", usaMedicamentoQual=" + usaMedicamentoQual + ", alergiaAnestesia=" + alergiaAnestesia
				+ ", alergiaAnestesiaQual=" + alergiaAnestesiaQual + ", gravidaOuAmamentando=" + gravidaOuAmamentando
				+ ", doencaCardioRespiratoria=" + doencaCardioRespiratoria + ", doencaCardioRespiratoriaQual="
				+ doencaCardioRespiratoriaQual + ", doencaoTransmissivel=" + doencaoTransmissivel
				+ ", doencaoTransmissivelQual=" + doencaoTransmissivelQual + ", diabetico=" + diabetico
				+ ", hipertencaoArterial=" + hipertencaoArterial + ", hemorragico=" + hemorragico + ", cirurgia="
				+ cirurgia + ", cirurgiaQual=" + cirurgiaQual + ", informacaoAdicional=" + informacaoAdicional + "]";
	}
	
}
