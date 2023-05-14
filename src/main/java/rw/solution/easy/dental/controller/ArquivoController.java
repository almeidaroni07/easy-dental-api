package rw.solution.easy.dental.controller;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import rw.solution.easy.dental.model.Arquivo;
import rw.solution.easy.dental.model.Response;
import rw.solution.easy.dental.service.ArquivoService;
import rw.solution.easy.dental.util.LogUtil;

@CrossOrigin
@RestController
@RequestMapping("/arquivo/v1/")
@Tag(name = "Arquivos", description = "Métodos de Arquivos")
public class ArquivoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1675099236408179256L;
	
	private static Logger log = Logger.getLogger(ArquivoController.class);
	
	@Autowired
	private ArquivoService service;
	
	
	
	@Operation(summary = "Recupera os arquivos cadastrados")
	@GetMapping(value = "/{customer}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Arquivo>> getArquivos(@PathVariable(required=true) Long customer) {
		
		try {
			List<Arquivo> response = this.service.getArquivos(customer);
			
			log.info(String.format(LogUtil.FORMATLOG, "arquivo", "getArquivos", " Response HTTP OK "+response.size()));
			return ResponseEntity.status(HttpStatus.OK).body(response);
			
		} catch (Exception e) {
			log.info(String.format(LogUtil.FORMATLOG, "arquivo", "getArquivos", " Error"+ e.getMessage()));
			e.printStackTrace();
		}
		
		log.info(String.format(LogUtil.FORMATLOG, "arquivo", "getArquivos", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@Operation(summary = "Recupera o arquivo pelo ID")
	@GetMapping(value = "/id/{customer}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Arquivo> getArquivoPorID(@PathVariable(required=true) Long customer,
													@RequestParam(required=true) Long arquivoID) {
		try {
			Arquivo response = this.service.getArquivoPorID(arquivoID);
			log.info(String.format(LogUtil.FORMATLOG, "arquivo", "getArquivoPorID", " Response HTTP OK"));
			return ResponseEntity.status(HttpStatus.OK).body(response);
			
		} catch (Exception e) {
			log.info(String.format(LogUtil.FORMATLOG, "arquivo", "getArquivoPorID", " Error"+ e.getMessage()));
			e.printStackTrace();
		}
		
		log.info(String.format(LogUtil.FORMATLOG, "arquivo", "getArquivoPorID", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@Operation(summary = "Adiciona um arquivo")
	@PostMapping(value = "/{customer}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@PathVariable(required=true) Long customer, 
									   @RequestBody(required=true) Arquivo parameter) {
	
		try {
			
			Response response = this.service.save(customer, parameter);
							
			if(response.isSuccess()){				
				log.info(String.format(LogUtil.FORMATLOG, "save", "arquivo", " Response HTTP OK"));
				return ResponseEntity.status(HttpStatus.OK).body(String.valueOf(response.getCdResponse()));
			}else{
				log.info(String.format(LogUtil.FORMATLOG, "save", "arquivo", " Response HTTP BAD REQUEST"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
			}
		} catch (Exception e) {
			log.error(String.format(LogUtil.FORMATLOG, "save", "arquivo", " Error"), e);
			e.printStackTrace();
		}
		log.info(String.format(LogUtil.FORMATLOG, "save", "arquivo", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@Operation(summary = "Atualiza um arquivo")
	@PutMapping(value = "/{customer}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@PathVariable(required=true) Long customer, 
									     @RequestParam(required=true) Long arquivoID,
									     @RequestBody(required=true) Arquivo parameter) {
	
		try {
			
			Response response = this.service.update(customer, arquivoID, parameter);
							
			if(response.isSuccess()){				
				log.info(String.format(LogUtil.FORMATLOG, "arquivo", "update", " Response HTTP OK"));
				return ResponseEntity.status(HttpStatus.OK).body(String.valueOf(response.getCdResponse()));
			}else{
				log.info(String.format(LogUtil.FORMATLOG, "arquivo", "update", " Response HTTP BAD REQUEST"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
			}
		} catch (Exception e) {
			log.error(String.format(LogUtil.FORMATLOG, "arquivo", "update", " Error"), e);
			e.printStackTrace();
		}
		log.info(String.format(LogUtil.FORMATLOG, "arquivo", "update", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@Operation(summary = "Deleta um arquivo")
	@DeleteMapping(value = "/{customer}")
	public ResponseEntity<String> delete(@PathVariable(required=true) Long customer,
										 @RequestParam(required=true) Long arquivoID) {
	
		try {
			
			Response response = this.service.delete(customer, arquivoID);
							
			if(response.isSuccess()){				
				log.info(String.format(LogUtil.FORMATLOG, "arquivo", "delete", " Response HTTP OK"));
				return ResponseEntity.status(HttpStatus.OK).body(response.getMessage());
			}else{
				log.info(String.format(LogUtil.FORMATLOG, "arquivo", "delete", " Response HTTP BAD REQUEST"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
			}
		} catch (Exception e) {
			log.error(String.format(LogUtil.FORMATLOG, "arquivo", "delete", " Error"), e);
			e.printStackTrace();
		}
		log.info(String.format(LogUtil.FORMATLOG, "arquivo", "delete", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	

	@Operation(summary = "Atualiza o blob do arquivo")
	@PostMapping(value = "/blob/{customer}")
	public ResponseEntity<String> updateArquivo(@PathVariable(required=true) Long customer,
				  	 						   @RequestParam(required=true) Long arquivoID, 
											   @RequestBody(required=true) MultipartFile arquivo) {
	
		try {
			
			Response response = this.service.updateArquivo(customer, arquivoID, arquivo);
							
			if(response.isSuccess()){				
				log.info(String.format(LogUtil.FORMATLOG, "updateArquivo", "arquivo", " Response HTTP OK"));
				return ResponseEntity.status(HttpStatus.OK).body("OK");
			}else{
				log.info(String.format(LogUtil.FORMATLOG, "updateArquivo", "arquivo", " Response HTTP BAD REQUEST"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
			}
		} catch (Exception e) {
			log.error(String.format(LogUtil.FORMATLOG, "updateArquivo", "arquivo", " Error"), e);
			e.printStackTrace();
		}
		log.info(String.format(LogUtil.FORMATLOG, "updateArquivo", "arquivo", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@Operation(summary = "Recupera o arquivo")
	@GetMapping(value = "/blob/{customer}")
	public ResponseEntity<Resource> getArquivo(@PathVariable(required=true) Long customer,
	   										   @RequestParam(required=true) Long arquivoID,
	   										   HttpServletRequest request) {
		
		try {
			byte[] response = this.service.getArquivo(customer, arquivoID);
			
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
				
				log.info(String.format(LogUtil.FORMATLOG, "getArquivo", "arquivo", " Response HTTP OK"));
				return ResponseEntity.status(HttpStatus.OK)
									 .contentType(MediaType.parseMediaType(contentType))
									 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resource.getFilename()+"\"")
									 .body(resource);
			}			
			
		} catch (Exception e) {
			log.info(String.format(LogUtil.FORMATLOG, "getArquivo", "arquivo", " Error"+ e.getMessage()));
			e.printStackTrace();
		}
		
		log.info(String.format(LogUtil.FORMATLOG, "getArquivo", "arquivo", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
}