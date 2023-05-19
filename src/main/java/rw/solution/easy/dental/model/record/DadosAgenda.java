package rw.solution.easy.dental.model.record;

import java.util.List;

public record DadosAgenda(List<DadosAgendamento> agendamentos,
						  List<DadosAgendamento> agendamentosDeHoje) {
	
	
	public DadosAgenda(List<DadosAgendamento> agendamentos, List<DadosAgendamento> agendamentosDeHoje) {
		this.agendamentos = agendamentos;
		this.agendamentosDeHoje = agendamentosDeHoje;
	}

}
