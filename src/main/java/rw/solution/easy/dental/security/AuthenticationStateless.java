package rw.solution.easy.dental.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rw.solution.easy.dental.model.UserStatus;
import rw.solution.easy.dental.model.repository.UsuarioRepository;
import rw.solution.easy.dental.util.LogUtil;

@Service
public class AuthenticationStateless implements UserDetailsService {
	
	
	private static Logger log = Logger.getLogger(AuthenticationStateless.class);
	
	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info(String.format(LogUtil.FORMATLOG, "loadUserByUsername", username, "username: "+username));
		return this.repository.getUsuarioByUsername(username, UserStatus.ATIVO);
	}

}
