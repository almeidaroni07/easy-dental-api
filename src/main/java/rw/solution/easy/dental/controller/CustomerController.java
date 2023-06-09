package rw.solution.easy.dental.controller;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import rw.solution.easy.dental.model.Response;
import rw.solution.easy.dental.service.CustomerService;
import rw.solution.easy.dental.util.LogUtil;

@CrossOrigin
@RestController
@RequestMapping("/customer/v1/")
@Tag(name = "Customer", description = "Métodos de Customer")
public class CustomerController implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3743045817625789965L;
	
	private static Logger log = Logger.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService service;
	
	@Autowired
	private ResourceLoader reourceLouder;

	@Operation(summary = "Atualiza o blob da foto")
	@PostMapping(value = "/logo/{customer}")
	public ResponseEntity<String> updateLogo(@PathVariable(required=true) Long customer, 
											 @RequestBody(required=true) MultipartFile logo) {
	
		try {
			
			Response response = this.service.updateLogo(customer, logo);
							
			if(response.isSuccess()){				
				log.info(String.format(LogUtil.FORMATLOG, "CustomerController", "updateLogo", " Response HTTP OK"));
				return ResponseEntity.status(HttpStatus.OK).body("OK");
			}else{
				log.info(String.format(LogUtil.FORMATLOG, "CustomerController", "updateLogo", " Response HTTP BAD REQUEST"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
			}
		} catch (Exception e) {
			log.error(String.format(LogUtil.FORMATLOG, "CustomerController", "updateLogo", " Error"), e);
			e.printStackTrace();
		}
		log.info(String.format(LogUtil.FORMATLOG, "CustomerController", "updateLogo", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@SuppressWarnings("unused")
	@Operation(summary = "Recupera a foto do usuario")
	@GetMapping(value = "/logo/{customer}")
	public ResponseEntity<Resource> getFoto(@PathVariable(required=true) Long customer,
	   										HttpServletRequest request) {
		
		try {
			byte[] response = this.service.getLogo(customer);
			
			log.info(String.format(LogUtil.FORMATLOG, "CustomerController", "getLogo", "byte[]: "+response));
			Resource resource = null;
			if(null != response) {
				resource = new ByteArrayResource(response);				
			}else {
				String location = "classpath:static/assets/img/logoDefault.png";
				resource = this.reourceLouder.getResource(location);
			}
				
				String contentType = null;
			
			try {
				contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
			} catch (Exception e) {
			}
			
			if(null == contentType) {
				contentType = "application/octet-stream";
			}
			
			log.info(String.format(LogUtil.FORMATLOG, "CustomerController", "getLogo", " Response HTTP OK"));
			return ResponseEntity.status(HttpStatus.OK)
								 .contentType(MediaType.parseMediaType(contentType))
								 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resource.getFilename()+"\"")
								 .body(resource);
						
			
		} catch (Exception e) {
			log.info(String.format(LogUtil.FORMATLOG, "CustomerController", "getLogo", " Error"+ e.getMessage()));
			e.printStackTrace();
		}
		
		log.info(String.format(LogUtil.FORMATLOG, "CustomerController", "getLogo", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
}
