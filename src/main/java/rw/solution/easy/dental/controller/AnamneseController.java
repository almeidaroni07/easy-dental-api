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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rw.solution.easy.dental.model.Anamnese;
import rw.solution.easy.dental.model.Response;
import rw.solution.easy.dental.service.AnamneseService;
import rw.solution.easy.dental.util.LogUtil;

@CrossOrigin
@RestController
@RequestMapping("/anamnese/v1/")
@Api(tags = {"Anamnese"})
public class AnamneseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1921326225092556871L;
	
	private static Logger log = Logger.getLogger(AnamneseController.class);
	
	@Autowired
	private AnamneseService service;
	
	@ApiOperation(value = "Recupera o paciente pelo ID", response = Anamnese.class)
	@GetMapping(value = "/id/{customer}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Anamnese> getAnamneseByPacienteID(@PathVariable(required=true) Long customer,
													 		@RequestParam(required=true) Long pacienteId) {
		try {
			Anamnese response = this.service.getAnamneseByPacienteID(pacienteId);
			log.info(String.format(LogUtil.FORMATLOG, "getAnamneseByPacienteID", "paciente", " Response HTTP OK"));
			return ResponseEntity.status(HttpStatus.OK).body(response);
			
		} catch (Exception e) {
			log.info(String.format(LogUtil.FORMATLOG, "getAnamneseByPacienteID", "paciente", " Error"+ e.getMessage()));
			e.printStackTrace();
		}
		
		log.info(String.format(LogUtil.FORMATLOG, "getAnamneseByPacienteID", "paciente", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@ApiOperation(value = "Atualiza a Anamnese de um paciente", response = String.class)
	@PutMapping(value = "/{customer}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateAnamnese(@PathVariable(required=true) Long customer,
											  	 @RequestParam(required=true) Long pacienteId,
											  	 @RequestBody(required=true) Anamnese parameter) {
	
		try {
			
			Response response = this.service.updateAnamnese(customer, pacienteId, parameter);
							
			if(response.isSuccess()){				
				log.info(String.format(LogUtil.FORMATLOG, "updateAnamnese", "paciente", " Response HTTP OK"));
				return ResponseEntity.status(HttpStatus.OK).body(response.getMessage());
			}else{
				log.info(String.format(LogUtil.FORMATLOG, "updateAnamnese", "paciente", " Response HTTP BAD REQUEST"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
			}
		} catch (Exception e) {
			log.error(String.format(LogUtil.FORMATLOG, "updateAnamnese", "paciente", " Error"), e);
			e.printStackTrace();
		}
		log.info(String.format(LogUtil.FORMATLOG, "updateAnamnese", "paciente", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}