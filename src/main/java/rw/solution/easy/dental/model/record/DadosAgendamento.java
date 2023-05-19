package rw.solution.easy.dental.model.record;

import java.time.LocalDate;

import rw.solution.easy.dental.model.Agenda;

public record DadosAgendamento(Long tratamentoID,
								Long agendamentoID,
								Long pacienteID,
								String procedimento,
								String paciente,
								LocalDate data,
								String inicio,
								String fim,
								String dataHoraInicio,
								String dataHoraFim,
								DadosAgendamentoCor cor) {
	
	

	
	public DadosAgendamento(Agenda agenda) {

		this(agenda.getTratamentoID(), 
			 agenda.getId(), 
			 agenda.getPaciente().getId(), 
			 agenda.getProcedimento().getNome(), 
			 agenda.getPaciente().getNome(), 
			 agenda.getDataConsulta(),
			 agenda.getStringInicio(),
			 agenda.getStringFim(),
			 agenda.getHoraInicioConsulta(),
			 agenda.getHoraFimConsulta(),
			 agenda.getDadosAgendamentoCor());
	
	}
	
}
