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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import rw.solution.easy.dental.model.Response;
import rw.solution.easy.dental.security.model.User;
import rw.solution.easy.dental.service.UserService;
import rw.solution.easy.dental.util.LogUtil;

@CrossOrigin
@RestController
@RequestMapping("/usuario/v1/")
@Tag(name = "Usuarios", description = "Métodos dos Usuarios")
public class UsuarioController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7751328437200068937L;
	
	private static Logger log = Logger.getLogger(UsuarioController.class);
	
	@Autowired
	private UserService service;
	
	@Autowired
	private ResourceLoader reourceLouder;
	
	@Operation(summary = "Recupera o usuário pelo ID")
	@GetMapping(value = "/id/{customer}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> buscarPorID(@PathVariable(required=true) Long customer,
	 										@RequestParam(required=true) Long usuarioID) {
		
		try {
			User response = this.service.buscarPorID(customer, usuarioID);
			
			log.info(String.format(LogUtil.FORMATLOG, "UsuarioController", "buscarPorID", " Response HTTP OK "));
			return ResponseEntity.status(HttpStatus.OK).body(response);
				
		} catch (Exception e) {
			log.info(String.format(LogUtil.FORMATLOG, "UsuarioController", "getAgendamentos", " Error"+ e.getMessage()));
			e.printStackTrace();
		}
		
		log.info(String.format(LogUtil.FORMATLOG, "UsuarioController", "getAgendamentos", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@Operation(summary = "Atualiza o usuario")
	@PutMapping(value = "/{customer}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateUsuario(@PathVariable(required=true) Long customer,
											  	@RequestParam(required=true) Long usuarioID,
											  	@RequestBody(required=true) User parameter) {
	
		try {
			
			Response response = this.service.updatePaciente(customer, usuarioID, parameter);
							
			if(response.isSuccess()){				
				log.info(String.format(LogUtil.FORMATLOG, "UsuarioController", "updateUsuario", " Response HTTP OK"));
				return ResponseEntity.status(HttpStatus.OK).body(response.getMessage());
			}else{
				log.info(String.format(LogUtil.FORMATLOG, "UsuarioController", "updateUsuario", " Response HTTP BAD REQUEST"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
			}
		} catch (Exception e) {
			log.error(String.format(LogUtil.FORMATLOG, "UsuarioController", "updateUsuario", " Error"), e);
			e.printStackTrace();
		}
		log.info(String.format(LogUtil.FORMATLOG, "UsuarioController", "updateUsuario", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@Operation(summary = "Atualiza o blob da foto")
	@PostMapping(value = "/foto/{customer}")
	public ResponseEntity<String> updateFoto(@PathVariable(required=true) Long customer,
				  	 						 @RequestParam(required=true) Long usuarioID, 
											 @RequestBody(required=true) MultipartFile arquivo) {
	
		try {
			
			Response response = this.service.updateFoto(customer, usuarioID, arquivo);
							
			if(response.isSuccess()){				
				log.info(String.format(LogUtil.FORMATLOG, "UsuarioController", "updateFoto", " Response HTTP OK"));
				return ResponseEntity.status(HttpStatus.OK).body("OK");
			}else{
				log.info(String.format(LogUtil.FORMATLOG, "UsuarioController", "updateFoto", " Response HTTP BAD REQUEST"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
			}
		} catch (Exception e) {
			log.error(String.format(LogUtil.FORMATLOG, "UsuarioController", "updateFoto", " Error"), e);
			e.printStackTrace();
		}
		log.info(String.format(LogUtil.FORMATLOG, "UsuarioController", "updateFoto", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@SuppressWarnings("unused")
	@Operation(summary = "Recupera a foto do usuario")
	@GetMapping(value = "/foto/{customer}")
	public ResponseEntity<Resource> getFoto(@PathVariable(required=true) Long customer,
	   										   @RequestParam(required=true) Long usuarioID,
	   										   HttpServletRequest request) {
		
		try {
			byte[] response = this.service.getFoto(customer, usuarioID);
			
			log.info(String.format(LogUtil.FORMATLOG, "UsuarioController", "getFoto", "byte[]: "+response));
			Resource resource = null;
			if(null != response) {
				resource = new ByteArrayResource(response);
				
			}else {
				String location = "classpath:static/assets/img/fotoDefault.jpg";
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
			
			log.info(String.format(LogUtil.FORMATLOG, "UsuarioController", "getFoto", " Response HTTP OK"));
			return ResponseEntity.status(HttpStatus.OK)
								 .contentType(MediaType.parseMediaType(contentType))
								 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resource.getFilename()+"\"")
								 .body(resource);
					
			
		} catch (Exception e) {
			log.info(String.format(LogUtil.FORMATLOG, "UsuarioController", "getFoto", " Error"+ e.getMessage()));
			e.printStackTrace();
		}
		
		log.info(String.format(LogUtil.FORMATLOG, "UsuarioController", "getFoto", " Response HTTP INTERNAL SERVER ERROR"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}
