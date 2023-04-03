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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rw.solution.easy.dental.model.Agenda;
import rw.solution.easy.dental.model.dto.AgendamentoDTO;
import rw.solution.easy.dental.service.AgendaService;
import rw.solution.easy.dental.util.LogUtil;

@CrossOrigin
@RestController
@RequestMapping("/agenda/v1/")
@Api(tags = {"Agenda"})
public class AgendaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5254044576493818622L;
	
	private static Logger log = Logger.getLogger(AgendaController.class);
	
	@Autowired
	private AgendaService service;
	
	@ApiOperation(value = "Recupera os pacientes", response = Agenda.class)
	@GetMapping(value = "/{customer}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AgendamentoDTO>> getAgendamentosHojeByPacienteID(@PathVariable(required=true) Long customer) {
		
		try {
			List<AgendamentoDTO> response = this.service.getAgendamentosHojeByPacienteID(customer);
			
			log.info(String.format(LogUtil.FORMATLOG, "getAgendamentosHojeByPacienteID", "agenda", " Response HTTP OK "+response.size()));
			return ResponseEntity.status(HttpStatus.OK).body(response);
			
		} catch (Exception e) {
			log.info(String.format(LogUtil.FORMATLOG, "getAgendamentosHojeByPacienteID", "agenda", " Error"+ e.getMessage()));
			e.printStackTrace();
		}
		
		log.info(String.format(LogUtil.FORMATLOG, "getAgendamentosHojeByPacienteID", "agenda", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}
