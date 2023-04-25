package rw.solution.easy.dental.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.solution.easy.dental.model.Agenda;
import rw.solution.easy.dental.model.dto.AgendaDto;
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
	
	public AgendaDto getAgendamentos(Long customer) throws Exception{
		
		log.info(String.format(LogUtil.FORMATLOG, "AgendaService", "getAgendamentos", "customer: "+customer));
		
		List<Agenda> agendamentos = this.repository.getAgendamentos(customer);
		log.info(String.format(LogUtil.FORMATLOG, "AgendaService", "getAgendamentos", " agendamentos "+agendamentos.size()));

		List<AgendamentoDTO> listAgendamentoDTO = new ArrayList<AgendamentoDTO>();
		agendamentos.stream().forEach(agenda -> listAgendamentoDTO.add(agenda.getAgendamentoDTO()));
		
		
		List<Agenda> hoje = this.repository.getAgendamentosHoje(customer, LocalDate.now());
		log.info(String.format(LogUtil.FORMATLOG, "AgendaService", "getAgendamentos", " agendamentos de hoje "+hoje.size()));

		List<AgendamentoDTO> listAgendamentoHoje = new ArrayList<AgendamentoDTO>();
		hoje.stream().forEach(agenda -> listAgendamentoHoje.add(agenda.getAgendamentoDTO()));
		
		return new AgendaDto(listAgendamentoDTO, listAgendamentoHoje);
	}

}
