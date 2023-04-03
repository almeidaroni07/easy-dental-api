package rw.solution.easy.dental.controller;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rw.solution.easy.dental.model.Paciente;
import rw.solution.easy.dental.model.PreAvaliacaoAnamnese;
import rw.solution.easy.dental.model.Response;
import rw.solution.easy.dental.service.PacienteService;
import rw.solution.easy.dental.util.LogUtil;

@CrossOrigin
@RestController
@RequestMapping("/paciente/v1/")
@Api(tags = {"Paciente"})
public class PacienteController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1795025199747261529L;
	
	private static Logger log = Logger.getLogger(PacienteController.class);
	
	@Autowired
	private PacienteService service;
	
	@ApiOperation(value = "Recupera os pacientes", response = Paciente.class)
	@GetMapping(value = "/{customer}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Paciente>> getPacientes(@PathVariable(required=true) Long customer) {
		
		try {
			List<Paciente> response = this.service.getPacientesByCustomer(customer);
			log.info(String.format(LogUtil.FORMATLOG, "getPacientes", "paciente", " Response HTTP OK"));
			return ResponseEntity.status(HttpStatus.OK).body(response);
			
		} catch (Exception e) {
			log.info(String.format(LogUtil.FORMATLOG, "getPacientes", "paciente", " Error"+ e.getMessage()));
			e.printStackTrace();
		}
		
		log.info(String.format(LogUtil.FORMATLOG, "getPacientes", "paciente", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@ApiOperation(value = "Recupera o paciente pelo ID", response = Paciente.class)
	@GetMapping(value = "/id/{customer}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Paciente> getPacientePorID(@PathVariable(required=true) Long customer,
													 @RequestParam(required=true) Long pacienteId) {
		try {
			Paciente response = this.service.getPacienteByID(pacienteId);
			log.info(String.format(LogUtil.FORMATLOG, "getPacientePorID", "paciente", " Response HTTP OK"));
			return ResponseEntity.status(HttpStatus.OK).body(response);
			
		} catch (Exception e) {
			log.info(String.format(LogUtil.FORMATLOG, "getPacientePorID", "paciente", " Error"+ e.getMessage()));
			e.printStackTrace();
		}
		
		log.info(String.format(LogUtil.FORMATLOG, "getPacientePorID", "paciente", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@ApiOperation(value = "Adiciona um Paciente", response = String.class)
	@PostMapping(value = "/{customer}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addPaciente(@PathVariable(required=true) Long customer, 
										  	  @RequestBody(required=true) Paciente parameter) {
	
		try {
			
			Response response = this.service.addPaciente(customer, parameter);
							
			if(response.isSuccess()){				
				log.info(String.format(LogUtil.FORMATLOG, "addPaciente", "paciente", " Response HTTP OK"));
				return ResponseEntity.status(HttpStatus.OK).body(response.getMessage());
			}else{
				log.info(String.format(LogUtil.FORMATLOG, "addPaciente", "paciente", " Response HTTP BAD REQUEST"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
			}
		} catch (Exception e) {
			log.error(String.format(LogUtil.FORMATLOG, "addPaciente", "paciente", " Error"), e);
			e.printStackTrace();
		}
		log.info(String.format(LogUtil.FORMATLOG, "addPaciente", "paciente", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@ApiOperation(value = "Atualiza um Paciente", response = String.class)
	@PutMapping(value = "/{customer}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updatePaciente(@PathVariable(required=true) Long customer,
											  	 @RequestParam(required=true) Long pacienteId,
											  	 @RequestBody(required=true) Paciente parameter) {
	
		try {
			
			Response response = this.service.updatePaciente(customer, pacienteId, parameter);
							
			if(response.isSuccess()){				
				log.info(String.format(LogUtil.FORMATLOG, "updatePaciente", "paciente", " Response HTTP OK"));
				return ResponseEntity.status(HttpStatus.OK).body(response.getMessage());
			}else{
				log.info(String.format(LogUtil.FORMATLOG, "updatePaciente", "paciente", " Response HTTP BAD REQUEST"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
			}
		} catch (Exception e) {
			log.error(String.format(LogUtil.FORMATLOG, "updatePaciente", "paciente", " Error"), e);
			e.printStackTrace();
		}
		log.info(String.format(LogUtil.FORMATLOG, "updatePaciente", "paciente", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@ApiOperation(value = "Deleta um Paciente", response = String.class)
	@DeleteMapping(value = "/{customer}")
	public ResponseEntity<String> deletePaciente(@PathVariable(required=true) Long customer,
											  	 @RequestParam(required=true) Long pacienteId) {
	
		try {
			
			Response response = this.service.delete(customer, pacienteId);
							
			if(response.isSuccess()){				
				log.info(String.format(LogUtil.FORMATLOG, "deletePaciente", "paciente", " Response HTTP OK"));
				return ResponseEntity.status(HttpStatus.OK).body(response.getMessage());
			}else{
				log.info(String.format(LogUtil.FORMATLOG, "deletePaciente", "paciente", " Response HTTP BAD REQUEST"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
			}
		} catch (Exception e) {
			log.error(String.format(LogUtil.FORMATLOG, "deletePaciente", "paciente", " Error"), e);
			e.printStackTrace();
		}
		log.info(String.format(LogUtil.FORMATLOG, "deletePaciente", "paciente", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@ApiOperation(value = "Recupera a pre avaliacao e a anamnese pelo ID", response = PreAvaliacaoAnamnese.class)
	@GetMapping(value = "/pre/anamnese/{customer}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PreAvaliacaoAnamnese> getAvaliacaoAnamnese(@PathVariable(required=true) Long customer,
													 				 @RequestParam(required=true) Long pacienteId) {
		try {
			PreAvaliacaoAnamnese response = this.service.getAvaliacaoAnamnese(customer, pacienteId);
			log.info(String.format(LogUtil.FORMATLOG, "getAvaliacaoAnamnese", "paciente", " Response HTTP OK"));
			return ResponseEntity.status(HttpStatus.OK).body(response);
			
		} catch (Exception e) {
			log.info(String.format(LogUtil.FORMATLOG, "getAvaliacaoAnamnese", "paciente", " Error"+ e.getMessage()));
			e.printStackTrace();
		}
		
		log.info(String.format(LogUtil.FORMATLOG, "getAvaliacaoAnamnese", "paciente", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@ApiOperation(value = "Atualiza a pre avaliacao e a anamnese", response = String.class)
	@PutMapping(value = "/pre/anamnese/{customer}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updatePreAnamnese(@PathVariable(required=true) Long customer,
											  	 	@RequestParam(required=true) Long pacienteId,
											  	 	@RequestBody(required=true) PreAvaliacaoAnamnese parameter) {
	
		try {
			
			Response response = this.service.updatePreAnamnese(customer, pacienteId, parameter);
							
			if(response.isSuccess()){				
				log.info(String.format(LogUtil.FORMATLOG, "updatePreAnamnese", "paciente", " Response HTTP OK"));
				return ResponseEntity.status(HttpStatus.OK).body(response.getMessage());
			}else{
				log.info(String.format(LogUtil.FORMATLOG, "updatePreAnamnese", "paciente", " Response HTTP BAD REQUEST"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
			}
		} catch (Exception e) {
			log.error(String.format(LogUtil.FORMATLOG, "updatePreAnamnese", "paciente", " Error"), e);
			e.printStackTrace();
		}
		log.info(String.format(LogUtil.FORMATLOG, "updatePreAnamnese", "paciente", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}
