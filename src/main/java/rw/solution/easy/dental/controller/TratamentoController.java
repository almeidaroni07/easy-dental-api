package rw.solution.easy.dental.controller;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import rw.solution.easy.dental.model.Response;
import rw.solution.easy.dental.model.record.DadosTratamento;
import rw.solution.easy.dental.service.TratamentoService;
import rw.solution.easy.dental.util.LogUtil;

@CrossOrigin
@RestController
@RequestMapping("/tratamento/v1/")
@Tag(name = "Tratamento", description = "Métodos dos Tratamentos")
public class TratamentoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5096865418881550419L;
	
	private static Logger log = Logger.getLogger(TratamentoController.class);
	
	@Autowired
	private TratamentoService service;
	
	@Operation(summary = "Adiciona um tratamento a um paciente")
	@PostMapping(value = "/{customer}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@PathVariable(required=true) Long customer,
		  	 						   @RequestParam(required=true) Long pacienteId, 
									   @RequestBody(required=true) DadosTratamento parameter) {
	
		try {
			
			Response response = this.service.save(customer, pacienteId, parameter);
							
			if(response.isSuccess()){				
				log.info(String.format(LogUtil.FORMATLOG, "save", "tratamento", " Response HTTP OK"));
				return ResponseEntity.status(HttpStatus.OK).body(String.valueOf(response.getCdResponse()));
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
	
	
	@Operation(summary = "Atualiza a assinatura de um paciente")
	@PostMapping(value = "/assinatura/{customer}")
	public ResponseEntity<String> updateAssinatura(@PathVariable(required=true) Long customer,
					  	 						   @RequestParam(required=true) Long tratamentoID, 
												   @RequestBody(required=true) MultipartFile assinatura) {
	
		try {
			
			Response response = this.service.updateAssinatura(customer, tratamentoID, assinatura);
							
			if(response.isSuccess()){				
				log.info(String.format(LogUtil.FORMATLOG, "updateAssinatura", "tratamento", " Response HTTP OK"));
				return ResponseEntity.status(HttpStatus.OK).body("OK");
			}else{
				log.info(String.format(LogUtil.FORMATLOG, "updateAssinatura", "tratamento", " Response HTTP BAD REQUEST"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
			}
		} catch (Exception e) {
			log.error(String.format(LogUtil.FORMATLOG, "updateAssinatura", "tratamento", " Error"), e);
			e.printStackTrace();
		}
		log.info(String.format(LogUtil.FORMATLOG, "updateAssinatura", "tratamento", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	
	@Operation(summary = "Recupera a assinatura")
	@GetMapping(value = "/assinatura/{customer}")
	public ResponseEntity<Resource> getArquivoAssinatura(@PathVariable(required=true) Long customer,
			   											 @RequestParam(required=true) Long tratamentoID,
			   											 HttpServletRequest request) {
		
		try {
			byte[] response = this.service.getArquivoAssinatura(customer, tratamentoID);
			
			if(null != response) {
				
				Resource resource = new ByteArrayResource(response);
				
				String contentType = null;
				
				try {
					contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
				} catch (Exception e) {
				}
				
				if(null == contentType) {
					contentType = "application/octet-stream";
				}
				
				log.info(String.format(LogUtil.FORMATLOG, "getArquivoAssinatura", "agenda", " Response HTTP OK"));
				return ResponseEntity.status(HttpStatus.OK)
									 .contentType(MediaType.parseMediaType(contentType))
									 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resource.getFilename()+"\"")
									 .body(resource);
			}			
			
		} catch (Exception e) {
			log.info(String.format(LogUtil.FORMATLOG, "getArquivoAssinatura", "agenda", " Error"+ e.getMessage()));
			e.printStackTrace();
		}
		
		log.info(String.format(LogUtil.FORMATLOG, "getArquivoAssinatura", "agenda", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}
