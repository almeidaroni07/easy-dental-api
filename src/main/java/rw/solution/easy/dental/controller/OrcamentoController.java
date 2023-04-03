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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rw.solution.easy.dental.model.Orcamento;
import rw.solution.easy.dental.model.OrcamentoProcedimento;
import rw.solution.easy.dental.service.OrcamentoService;
import rw.solution.easy.dental.util.LogUtil;

@CrossOrigin
@RestController
@RequestMapping("/orcamento/v1/")
@Api(tags = {"Orcamento"})
public class OrcamentoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4856753364309033834L;
	
	private static Logger log = Logger.getLogger(OrcamentoController.class);
	
	@Autowired
	private OrcamentoService service;
	
	@ApiOperation(value = "Recupera os orcamentos", response = Orcamento.class)
	@GetMapping(value = "/{customer}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Orcamento>> getOrcamentos(@PathVariable(required=true) Long customer) {
		
		try {
			List<Orcamento> response = this.service.getOrcamentos(customer);
			
			log.info(String.format(LogUtil.FORMATLOG, "getOrcamentos", "orcamento", " Response HTTP OK "+response.size()));
			return ResponseEntity.status(HttpStatus.OK).body(response);
			
		} catch (Exception e) {
			log.info(String.format(LogUtil.FORMATLOG, "getOrcamentos", "orcamento", " Error"+ e.getMessage()));
			e.printStackTrace();
		}
		
		log.info(String.format(LogUtil.FORMATLOG, "getOrcamentos", "orcamento", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@ApiOperation(value = "Recupera os procedimentos do orcamento", response = OrcamentoProcedimento.class)
	@GetMapping(value = "/procedimento/{customer}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrcamentoProcedimento>> getProcedimentosOrcamento(@PathVariable(required=true) Long customer,
			 											 						 @RequestParam(required=true) Long orcamentoID) {
		
		try {
			List<OrcamentoProcedimento> response = this.service.getProcedimentosOrcamento(customer, orcamentoID);
			
			log.info(String.format(LogUtil.FORMATLOG, "getProcedimentosOrcamento", "orcamento", " Response HTTP OK "+response.size()));
			return ResponseEntity.status(HttpStatus.OK).body(response);
			
		} catch (Exception e) {
			log.info(String.format(LogUtil.FORMATLOG, "getProcedimentosOrcamento", "orcamento", " Error"+ e.getMessage()));
			e.printStackTrace();
		}
		
		log.info(String.format(LogUtil.FORMATLOG, "getProcedimentosOrcamento", "orcamento", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}
