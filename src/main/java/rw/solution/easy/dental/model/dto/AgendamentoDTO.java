package rw.solution.easy.dental.model.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class AgendamentoDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -44254502073402358L;
	
	private Long agendamentoID;
	private String procedimento;
	private String paciente;
	private LocalDate data;
	private String inicio;
	private String fim;
	private String dataHoraInicio;
	private String dataHoraFim;
	
	public AgendamentoDTO() {
		// TODO Auto-generated constructor stub
	}

	public AgendamentoDTO(Long agendamentoID, String procedimento, String paciente, LocalDate data, String inicio,
			String fim, String dataHoraInicio, String dataHoraFim) {
		super();
		this.agendamentoID = agendamentoID;
		this.procedimento = procedimento;
		this.paciente = paciente;
		this.data = data;
		this.inicio = inicio;
		this.fim = fim;
		this.dataHoraInicio = dataHoraInicio;
		this.dataHoraFim = dataHoraFim;
	}

	public Long getAgendamentoID() {
		return agendamentoID;
	}

	public void setAgendamentoID(Long agendamentoID) {
		this.agendamentoID = agendamentoID;
	}

	public String getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
	}

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFim() {
		return fim;
	}

	public void setFim(String fim) {
		this.fim = fim;
	}

	public String getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(String dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public String getDataHoraFim() {
		return dataHoraFim;
	}

	public void setDataHoraFim(String dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}
}