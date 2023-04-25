package rw.solution.easy.dental.service;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import rw.solution.easy.dental.model.Response;
import rw.solution.easy.dental.model.repository.AuthenticationRepository;
import rw.solution.easy.dental.security.model.User;
import rw.solution.easy.dental.util.LogUtil;

@Service
public class UserService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5348845115650831616L;
	
	private static Logger log = Logger.getLogger(UserService.class);
	
	@Autowired
	private AuthenticationRepository repository;

	public User buscarPorID(Long customer, Long usuarioID) throws Exception {
		log.info(String.format(LogUtil.FORMATLOG, "UserService", "buscarPorID", "usuarioID: "+usuarioID));
		return this.repository.getUserAuthByUserID(usuarioID);
	}

	public Response updatePaciente(Long customer, Long usuarioID, User parameter) throws Exception {
		
		log.info(String.format(LogUtil.FORMATLOG, "UserService", "buscarPorID", "usuarioID: "+usuarioID));
		User user = this.repository.getUserAuthByUserID(usuarioID);
		
		log.info(String.format(LogUtil.FORMATLOG, "UserService", "buscarPorID", "password: "+parameter.getPassword()));
		if(null != parameter.getPassword()) {
			user.setPassword(parameter.getPassword());
		}
		
		user.setNome(parameter.getNome());
		user.setUsername(parameter.getUsername());
		
		this.repository.save(user);
		
		return new Response(true, "Usuario atualizado com sucesso");
	}

	public Response updateFoto(Long customer, Long usuarioID, MultipartFile arquivo) throws Exception {
		log.info(String.format(LogUtil.FORMATLOG, "UserService", "updateFoto", "usuarioID: "+usuarioID));
		User user = this.repository.getUserAuthByUserID(usuarioID);
		byte[] arquivoBytes = arquivo.getBytes();
		log.info(String.format(LogUtil.FORMATLOG, "UserService", "updateFoto", "byte[]: "+arquivoBytes));
		user.setFoto(arquivoBytes);
		
		this.repository.save(user);
		
		return new Response(true, "Foto atualizado com sucesso.");
	}

	public byte[] getFoto(Long customer, Long usuarioID) throws Exception {
		log.info(String.format(LogUtil.FORMATLOG, "UserService", "getFoto", "usuarioID: "+usuarioID));
		return this.repository.getFoto(usuarioID);
	}

}
