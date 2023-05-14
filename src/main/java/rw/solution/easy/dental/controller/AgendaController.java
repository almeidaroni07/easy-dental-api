package rw.solution.easy.dental.controller;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import rw.solution.easy.dental.model.dto.AgendaDto;
import rw.solution.easy.dental.service.AgendaService;
import rw.solution.easy.dental.util.LogUtil;

@CrossOrigin
@RestController
@RequestMapping("/agenda/v1/")
@Tag(name = "Agenda", description = "Métodos da agenda")
public class AgendaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5254044576493818622L;
	
	private static Logger log = Logger.getLogger(AgendaController.class);
	
	@Autowired
	private AgendaService service;
	
	@Operation(summary = "Recupera os agendamentos")
	@GetMapping(value = "/{customer}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AgendaDto> getAgendamentos(@PathVariable(required=true) Long customer) {
		
		try {
			AgendaDto response = this.service.getAgendamentos(customer);
			
			log.info(String.format(LogUtil.FORMATLOG, "AgendaController", "getAgendamentos", " Response HTTP OK "));
			return ResponseEntity.status(HttpStatus.OK).body(response);
				
		} catch (Exception e) {
			log.info(String.format(LogUtil.FORMATLOG, "AgendaController", "getAgendamentos", " Error"+ e.getMessage()));
			e.printStackTrace();
		}
		
		log.info(String.format(LogUtil.FORMATLOG, "AgendaController", "getAgendamentos", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
