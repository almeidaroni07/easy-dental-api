package rw.solution.easy.dental.filter;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rw.solution.easy.dental.model.User;
import rw.solution.easy.dental.service.AuthenticationService;
import rw.solution.easy.dental.util.LogUtil;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {
	
	private static Logger log = Logger.getLogger(AuthenticationFilter.class);

	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		
		try {
			
			String token = recuperarTokenJWT(request);
			if(null != token) {		
				
				String subject = this.authenticationService.getSubject(token);
				
				log.info(String.format(LogUtil.FORMATLOG, "doFilterInternal", request.getRequestURI(), "token: "+token));
				log.info(String.format(LogUtil.FORMATLOG, "doFilterInternal", request.getRequestURI(), "subject: "+subject));			
				
				User agent = this.authenticationService.getUsuarioByUsername(subject);
				
				UsernamePasswordAuthenticationToken authentication = new
			    UsernamePasswordAuthenticationToken(agent,null, agent.getAuthorities());
			    SecurityContextHolder.getContext().setAuthentication(authentication);
				
				
				/*
				 * Long cdCustomer = getCustomer(request);
				 * 
				 * 
				 * if(cdCustomer == agent.getCustomer().getId()) {
				 * 
				 * 
				 * }else { log.info(String.format(LogUtil.FORMATLOG, "doFilterInternal",
				 * request.getRequestURI(), "Dentista sem acesso ao customer.")); }
				 */
			}
							
			
		} catch (Exception e) {
			log.error(String.format(LogUtil.FORMATLOG, "doFilterInternal", "System", "Error"), e);
		}
		
		filterChain.doFilter(request, response);
		
	}

	private String recuperarTokenJWT(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		if(null != authorization) {
			return authorization.replace("Bearer ", "");
		}
		return null;
	}

	
	/*
	 * private Long getCustomer(HttpServletRequest request) { try { String
	 * requestURI [] = request.getRequestURI().split("[/]"); String cdCustomer =
	 * requestURI[requestURI.length - 1]; return Long.parseLong(cdCustomer);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return null; }
	 */

}
