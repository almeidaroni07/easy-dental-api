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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import rw.solution.easy.dental.model.Response;
import rw.solution.easy.dental.model.record.DadosPreAvaliacao;
import rw.solution.easy.dental.service.PreAvaliacaoService;
import rw.solution.easy.dental.util.LogUtil;

@CrossOrigin
@RestController
@RequestMapping("/pre/avaliacao/v1/")
@Tag(name = "Pré Avaliação", description = "Métodos da pré avaliação")
public class PreAvaliacaoController implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6897061140138341199L;
	
	private static Logger log = Logger.getLogger(PreAvaliacaoController.class);
	
	@Autowired
	private PreAvaliacaoService service;
	
	@Operation(summary = "Recupera a pré avaliação de um paciente")
	@GetMapping(value = "/id/{customer}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DadosPreAvaliacao> getPreAvaliacaoByPacienteID(@PathVariable(required=true) Long customer,
													 					 @RequestParam(required=true) Long pacienteId) {
		
			DadosPreAvaliacao response = this.service.getPreAvaliacaoByPacienteID(pacienteId);
			log.info(String.format(LogUtil.FORMATLOG, "getPreAvaliacaoByPacienteID", "paciente", " Response HTTP OK"));
			return ResponseEntity.status(HttpStatus.OK).body(response);

	}
	
	@Operation(summary = "Atualiza a pre avaliacao de um paciente")
	@PutMapping(value = "/{customer}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updatePreAvaliacao(@PathVariable(required=true) Long customer,
											  	 	 @RequestParam(required=true) Long pacienteId,
											  	 	 @RequestBody(required=true) DadosPreAvaliacao parameter) {
		
		Response response = this.service.updatePreAvaliacao(customer, pacienteId, parameter);
						
		if(response.isSuccess()){				
			log.info(String.format(LogUtil.FORMATLOG, "updatePreAvaliacao", "paciente", " Response HTTP OK"));
			return ResponseEntity.status(HttpStatus.OK).body(response.getMessage());
		}else{
			log.info(String.format(LogUtil.FORMATLOG, "updatePreAvaliacao", "paciente", " Response HTTP BAD REQUEST"));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
		}

	}

}
