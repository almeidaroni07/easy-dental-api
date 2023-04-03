package rw.solution.easy.dental.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import rw.solution.easy.dental.security.model.User;
import rw.solution.easy.dental.service.AuthenticationService;
import rw.solution.easy.dental.util.LogUtil;

public class AuthenticationFilter extends OncePerRequestFilter {
	
	private static Logger log = Logger.getLogger(AuthenticationFilter.class);

	
	private AuthenticationService authenticationService;
	
	public AuthenticationFilter(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		String token = getToken(request);
		
		try {
			
			boolean valid = this.authenticationService.isTokenValid(token);
			
			if (valid) {
				
				Long userID = this.authenticationService.getUserID(token);
				Long cdCustomer = getCustomer(request);
				
				User agent = this.authenticationService.getUserAuthByUserID(userID);
				
				if(cdCustomer == agent.getCustomer().getId()) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(agent,null, agent.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(authentication);
					
				}else {
					log.info(String.format(LogUtil.FORMATLOG, "doFilterInternal", request.getRequestURI(), "Dentista sem acesso ao customer."));						
				}
							
			}else{
				log.info(String.format(LogUtil.FORMATLOG, "doFilterInternal", request.getRequestURI(), "Token invalido"));
			}
			
		} catch (Exception e) {
			log.error(String.format(LogUtil.FORMATLOG, "doFilterInternal", "System", "Error"), e);
		}
		
		filterChain.doFilter(request, response);
		
	}

	private String getToken(HttpServletRequest request) {
		
		try {
			String token = request.getHeader("Authorization");
			if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
				return null;
			}
			
			return token.substring(7, token.length());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	private Long getCustomer(HttpServletRequest request) {
		try {
			String requestURI [] = request.getRequestURI().split("[/]");
			String cdCustomer = requestURI[requestURI.length - 1];
			return Long.parseLong(cdCustomer);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
