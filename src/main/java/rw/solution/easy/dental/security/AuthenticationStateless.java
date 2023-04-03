package rw.solution.easy.dental.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rw.solution.easy.dental.model.repository.AuthenticationRepository;
import rw.solution.easy.dental.security.model.User;
import rw.solution.easy.dental.util.LogUtil;

@Service
public class AuthenticationStateless implements UserDetailsService {
	
	
	private static Logger log = Logger.getLogger(AuthenticationStateless.class);
	
	@Autowired
	private AuthenticationRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			
			log.info(String.format(LogUtil.FORMATLOG, "loadUserByUsername", username, "username: "+username));
			User user = this.repository.getUserByUsername(username);
			
			if(null != user){

				if("ATIVO".equals(user.getStatus().toString())) {
					
					return user;
					
				}else{
					log.info(String.format(LogUtil.FORMATLOG, "loadUserByUsername", username, "user inativo."));
					throw new UsernameNotFoundException("User inativo. "+username);
				}
				
			}else{
				log.info(String.format(LogUtil.FORMATLOG, "loadUserByUsername", username, "Usuario nao existe"));
				throw new UsernameNotFoundException("Dados inválidos: usuario nao existe");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new UsernameNotFoundException("Dados inválidos!");
	}

}
