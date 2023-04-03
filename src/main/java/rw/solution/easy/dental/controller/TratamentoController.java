package rw.solution.easy.dental.controller;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rw.solution.easy.dental.model.Response;
import rw.solution.easy.dental.model.Tratamento;
import rw.solution.easy.dental.service.TratamentoService;
import rw.solution.easy.dental.util.LogUtil;

@CrossOrigin
@RestController
@RequestMapping("/tratamento/v1/")
@Api(tags = {"Tratamento"})
public class TratamentoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5096865418881550419L;
	
	private static Logger log = Logger.getLogger(TratamentoController.class);
	
	@Autowired
	private TratamentoService service;
	
	@ApiOperation(value = "Adiciona um tratamento a um paciente", response = String.class)
	@PostMapping(value = "/{customer}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@PathVariable(required=true) Long customer,
		  	 						   @RequestParam(required=true) Long pacienteId, 
									   @RequestBody(required=true) Tratamento parameter) {
	
		try {
			
			Response response = this.service.save(customer, pacienteId, parameter);
							
			if(response.isSuccess()){				
				log.info(String.format(LogUtil.FORMATLOG, "save", "tratamento", " Response HTTP OK"));
				return ResponseEntity.status(HttpStatus.OK).body(response.getMessage());
			}else{
				log.info(String.format(LogUtil.FORMATLOG, "save", "tratamento", " Response HTTP BAD REQUEST"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
			}
		} catch (Exception e) {
			log.error(String.format(LogUtil.FORMATLOG, "save", "tratamento", " Error"), e);
			e.printStackTrace();
		}
		log.info(String.format(LogUtil.FORMATLOG, "save", "tratamento", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}
