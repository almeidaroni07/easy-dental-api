package rw.solution.easy.dental.model.dto;

import java.io.Serializable;
import java.util.List;

public class AgendaDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2157282112953403852L;
	
	private List<AgendamentoDTO> agendamentos;
	private List<AgendamentoDTO> agendamentosDeHoje;
	
	public AgendaDto() {
		// TODO Auto-generated constructor stub
	}
	
	public AgendaDto(List<AgendamentoDTO> agendamentos, List<AgendamentoDTO> agendamentosDeHoje) {
		super();
		this.agendamentos = agendamentos;
		this.agendamentosDeHoje = agendamentosDeHoje;
	}

	public List<AgendamentoDTO> getAgendamentos() {
		return agendamentos;
	}
	public void setAgendamentos(List<AgendamentoDTO> agendamentos) {
		this.agendamentos = agendamentos;
	}
	public List<AgendamentoDTO> getAgendamentosDeHoje() {
		return agendamentosDeHoje;
	}
	public void setAgendamentosDeHoje(List<AgendamentoDTO> agendamentosDeHoje) {
		this.agendamentosDeHoje = agendamentosDeHoje;
	} 
	
	
}
