package rw.solution.easy.dental.controller;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import rw.solution.easy.dental.model.Response;
import rw.solution.easy.dental.model.record.DadosAgenda;
import rw.solution.easy.dental.model.record.DadosStatusAgendamento;
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
	public ResponseEntity<DadosAgenda> getAgendamentos(@PathVariable(required=true) Long customer) {
		
		try {
			DadosAgenda response = this.service.getAgendamentos(customer);
			
			log.info(String.format(LogUtil.FORMATLOG, "AgendaController", "getAgendamentos", " Response HTTP OK "));
			return ResponseEntity.status(HttpStatus.OK).body(response);
				
		} catch (Exception e) {
			log.info(String.format(LogUtil.FORMATLOG, "AgendaController", "getAgendamentos", " Error"+ e.getMessage()));
			e.printStackTrace();
		}
		
		log.info(String.format(LogUtil.FORMATLOG, "AgendaController", "getAgendamentos", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@Operation(summary = "Recupera os status do agendamento")
	@GetMapping(value = "/status/{customer}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DadosStatusAgendamento>> getStatusAgendamento(@PathVariable(required=true) Long customer) {
		
		List<DadosStatusAgendamento> response = this.service.getStatusAgendamento(customer);
			
		log.info(String.format(LogUtil.FORMATLOG, "AgendaController", "getStatusAgendamento", " Response HTTP OK "));
		return ResponseEntity.status(HttpStatus.OK).body(response);
	
	}
	
	@Operation(summary = "Atualiza o status da consulta")
	@PutMapping(value = "/status/{customer}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@PathVariable(required=true) Long customer, 
									     @RequestParam(required=true) Long agendamentoID,
									     @RequestBody(required=true) DadosStatusAgendamento parameter) {
	
		Response response = this.service.updateStatusConsulta(customer, agendamentoID, parameter);
						
		if(response.isSuccess()){				
			log.info(String.format(LogUtil.FORMATLOG, "AgendaController", "getStatusAgendamento", " Response HTTP OK"));
			return ResponseEntity.status(HttpStatus.OK).body(String.valueOf(response.getCdResponse()));
		}else{
			log.info(String.format(LogUtil.FORMATLOG, "AgendaController", "getStatusAgendamento", " Response HTTP BAD REQUEST"));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
		}
	}
	
}
