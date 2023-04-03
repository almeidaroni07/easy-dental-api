package rw.solution.easy.dental.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.solution.easy.dental.model.Agenda;
import rw.solution.easy.dental.model.dto.AgendamentoDTO;
import rw.solution.easy.dental.model.repository.AgendaRepository;
import rw.solution.easy.dental.util.LogUtil;

@Service
public class AgendaService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2710415565600613446L;
	
	private static Logger log = Logger.getLogger(AgendaService.class);
	
	@Autowired
	private AgendaRepository repository;
	
	public List<AgendamentoDTO> getAgendamentosHojeByPacienteID(Long customer) throws Exception{
		
		log.info(String.format(LogUtil.FORMATLOG, "getAvaliacaoAnamnese", "paciente", "customer: "+customer));
		log.info(String.format(LogUtil.FORMATLOG, "getAvaliacaoAnamnese", "paciente", "Buscando os agendamento de hoje"));
		
		List<Agenda> agendamentos = this.repository.getAgendamentosHojeByPacienteID(customer);
		log.info(String.format(LogUtil.FORMATLOG, "getAgendamentosHojeByPacienteID", "agenda", " agendamentos "+agendamentos.size()));

		List<AgendamentoDTO> response = new ArrayList<AgendamentoDTO>();
		agendamentos.stream().forEach(agenda -> response.add(agenda.getAgendamentoDTO()));
		
		return response;
	}

}
