package rw.solution.easy.dental.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.apache.log4j.Logger;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import rw.solution.easy.dental.model.convert.LocalDateConverter;
import rw.solution.easy.dental.model.dto.AgendamentoCorDto;
import rw.solution.easy.dental.model.dto.AgendamentoDTO;
import rw.solution.easy.dental.model.enums.StatusConsulta;
import rw.solution.easy.dental.util.LogUtil;

@Entity
@Table(name = "agenda")
public class Agenda implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6069448562069599524L;
	
	private static Logger log = Logger.getLogger(Agenda.class);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "paciente_id", referencedColumnName = "id")
	private Paciente paciente;
	
	@Column(name = "tratamento_id")
	private Long tratamentoID;

	@Column(name = "data_consulta")
	@Convert(converter = LocalDateConverter.class)
	private LocalDate dataConsulta;
	
	@Column(name = "hora_inicio_consulta")
	private String horaInicioConsulta;
	
	@Column(name = "hora_fim_consulta")
	private String horaFimConsulta;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private StatusConsulta status;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "procedimento_id", referencedColumnName = "id")
	private Procedimento procedimento;
	
	
	public Agenda() {
		// TODO Auto-generated constructor stub
	}

	public Agenda(Paciente paciente, LocalDate dataConsulta, String horaInicioConsulta, String horaFimConsulta,
			StatusConsulta status, Procedimento procedimento, Long tratamentoID) {
		super();
		this.paciente = paciente;
		this.dataConsulta = dataConsulta;
		this.horaInicioConsulta = horaInicioConsulta;
		this.horaFimConsulta = horaFimConsulta;
		this.status = status;
		this.procedimento = procedimento;
		this.tratamentoID = tratamentoID;
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

	public LocalDate getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(LocalDate dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public String getHoraInicioConsulta() {
		return horaInicioConsulta;
	}

	public void setHoraInicioConsulta(String horaInicioConsulta) {
		this.horaInicioConsulta = horaInicioConsulta;
	}

	public String getHoraFimConsulta() {
		return horaFimConsulta;
	}

	public void setHoraFimConsulta(String horaFimConsulta) {
		this.horaFimConsulta = horaFimConsulta;
	}

	public StatusConsulta getStatus() {
		return status;
	}

	public void setStatus(StatusConsulta status) {
		this.status = status;
	}

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

	public Long getTratamentoID() {
		return tratamentoID;
	}

	public void setTratamentoID(Long tratamentoID) {
		this.tratamentoID = tratamentoID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataConsulta, horaFimConsulta, horaInicioConsulta, id, paciente, procedimento, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agenda other = (Agenda) obj;
		return Objects.equals(dataConsulta, other.dataConsulta)
				&& Objects.equals(horaFimConsulta, other.horaFimConsulta)
				&& Objects.equals(horaInicioConsulta, other.horaInicioConsulta) && Objects.equals(id, other.id)
				&& Objects.equals(paciente, other.paciente) && Objects.equals(procedimento, other.procedimento)
				&& status == other.status;
	}

	@Override
	public String toString() {
		return "Agenda [id=" + id + ", paciente=" + paciente + ", dataConsulta=" + dataConsulta
				+ ", horaInicioConsulta=" + horaInicioConsulta + ", horaFimConsulta=" + horaFimConsulta + ", status="
				+ status + ", procedimento=" + procedimento + "]";
	}
	
	public AgendamentoDTO getAgendamentoDTO() {
		try {
			
			String stringInicio = this.dataConsulta.toString();
			stringInicio = stringInicio + ' ' +this.horaInicioConsulta;
			log.info(String.format(LogUtil.FORMATLOG, "getAgendamentoDTO", "paciente", "stringInicio: "+stringInicio));
		
			String stringFim = this.dataConsulta.toString();
			stringFim = stringFim + ' ' +this.horaFimConsulta;
			log.info(String.format(LogUtil.FORMATLOG, "getAgendamentoDTO", "paciente", "stringFim: "+stringFim));
		
			return new AgendamentoDTO(this.id, this.getProcedimento().getNome(), this.getPaciente().getNome(), this.getDataConsulta(),  this.horaInicioConsulta,  this.horaFimConsulta, stringInicio, stringFim, this.tratamentoID, this.getAgendamentoCor(), this.getPaciente().getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	private AgendamentoCorDto getAgendamentoCor() {
		return new AgendamentoCorDto(this.getProcedimento().getCor(), "#FAE3E3");
	}
	
}
