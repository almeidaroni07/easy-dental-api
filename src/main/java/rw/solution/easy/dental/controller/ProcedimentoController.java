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
import rw.solution.easy.dental.model.Procedimento;
import rw.solution.easy.dental.model.Response;
import rw.solution.easy.dental.service.ProcedimentoService;
import rw.solution.easy.dental.util.LogUtil;

@CrossOrigin
@RestController
@RequestMapping("/procedimento/v1/")
@Api(tags = {"Procedimento"})
public class ProcedimentoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1252249464347966505L;
	
	private static Logger log = Logger.getLogger(ProcedimentoController.class);
	
	@Autowired
	private ProcedimentoService service;
	
	@ApiOperation(value = "Recupera os procedimentos", response = Procedimento.class)
	@GetMapping(value = "/{customer}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Procedimento>> getProcedimentos(@PathVariable(required=true) Long customer) {
		
		try {
			List<Procedimento> response = this.service.getProcedimentos(customer);
			log.info(String.format(LogUtil.FORMATLOG, "getProcedimentos", "procedimento", " Response HTTP OK"));
			return ResponseEntity.status(HttpStatus.OK).body(response);
			
		} catch (Exception e) {
			log.info(String.format(LogUtil.FORMATLOG, "getProcedimentos", "procedimento", " Error"+ e.getMessage()));
			e.printStackTrace();
		}
		
		log.info(String.format(LogUtil.FORMATLOG, "getProcedimentos", "procedimento", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@ApiOperation(value = "Recupera um procedimento pelo ID", response = Procedimento.class)
	@GetMapping(value = "/id/{customer}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Procedimento> getProcedimentoPorID(@PathVariable(required=true) Long customer,
													 	 @RequestParam(required=true) Long procedimentoID) {
		try {
			Procedimento response = this.service.getPacienteByID(procedimentoID);
			log.info(String.format(LogUtil.FORMATLOG, "getProcedimentoPorID", "procedimento", " Response HTTP OK"));
			return ResponseEntity.status(HttpStatus.OK).body(response);
			
		} catch (Exception e) {
			log.info(String.format(LogUtil.FORMATLOG, "getProcedimentoPorID", "procedimento", " Error"+ e.getMessage()));
			e.printStackTrace();
		}
		
		log.info(String.format(LogUtil.FORMATLOG, "getProcedimentoPorID", "procedimento", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@ApiOperation(value = "Adiciona um procedimento", response = String.class)
	@PostMapping(value = "/{customer}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@PathVariable(required=true) Long customer,
									   @RequestBody(required=true) Procedimento parameter) {
	
		try {
			
			Response response = this.service.save(customer, parameter);
							
			if(response.isSuccess()){				
				log.info(String.format(LogUtil.FORMATLOG, "save", "procedimento", " Response HTTP OK"));
				return ResponseEntity.status(HttpStatus.OK).body(response.getMessage());
			}else{
				log.info(String.format(LogUtil.FORMATLOG, "save", "procedimento", " Response HTTP BAD REQUEST"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
			}
		} catch (Exception e) {
			log.error(String.format(LogUtil.FORMATLOG, "save", "procedimento", " Error"), e);
			e.printStackTrace();
		}
		log.info(String.format(LogUtil.FORMATLOG, "save", "procedimento", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@ApiOperation(value = "Atualiza um procedimento", response = String.class)
	@PutMapping(value = "/{customer}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@PathVariable(required=true) Long customer,
										 @RequestParam(required=true) Long procedimentoID,
										 @RequestBody(required=true) Procedimento parameter) {
	
		try {
			
			Response response = this.service.update(customer, procedimentoID, parameter);
							
			if(response.isSuccess()){				
				log.info(String.format(LogUtil.FORMATLOG, "update", "procedimento", " Response HTTP OK"));
				return ResponseEntity.status(HttpStatus.OK).body(response.getMessage());
			}else{
				log.info(String.format(LogUtil.FORMATLOG, "update", "procedimento", " Response HTTP BAD REQUEST"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
			}
		} catch (Exception e) {
			log.error(String.format(LogUtil.FORMATLOG, "update", "procedimento", " Error"), e);
			e.printStackTrace();
		}
		log.info(String.format(LogUtil.FORMATLOG, "update", "procedimento", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@ApiOperation(value = "Deleta um procedimento", response = String.class)
	@DeleteMapping(value = "/{customer}")
	public ResponseEntity<String> delete(@PathVariable(required=true) Long customer,
										 @RequestParam(required=true) Long procedimentoID) {
	
		try {
			
			Response response = this.service.delete(customer, procedimentoID);
							
			if(response.isSuccess()){				
				log.info(String.format(LogUtil.FORMATLOG, "delete", "procedimento", " Response HTTP OK"));
				return ResponseEntity.status(HttpStatus.OK).body(response.getMessage());
			}else{
				log.info(String.format(LogUtil.FORMATLOG, "delete", "procedimento", " Response HTTP BAD REQUEST"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
			}
		} catch (Exception e) {
			log.error(String.format(LogUtil.FORMATLOG, "delete", "procedimento", " Error"), e);
			e.printStackTrace();
		}
		log.info(String.format(LogUtil.FORMATLOG, "delete", "procedimento", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}
