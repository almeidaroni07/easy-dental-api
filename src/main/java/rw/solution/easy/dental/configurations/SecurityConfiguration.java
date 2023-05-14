package rw.solution.easy.dental.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import rw.solution.easy.dental.security.filter.AuthenticationFilter;


@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
	
	
	
	@Autowired
	private AuthenticationFilter securityFilter;
	
	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable();
		
		return http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			   .and()
			   .authorizeHttpRequests()
			   .requestMatchers(HttpMethod.GET, "/tratamento/v1/assinatura/**").permitAll()
			   .requestMatchers(HttpMethod.GET, "/arquivo/v1/blob/**").permitAll()
			   .requestMatchers(HttpMethod.GET, "/usuario/v1/foto/**").permitAll()
			   .requestMatchers(HttpMethod.GET, "/customer/v1/logo/**").permitAll()
			   .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
			   .requestMatchers(HttpMethod.GET, "/keepalive/status").permitAll()
			   .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
			   .requestMatchers(HttpMethod.GET, "/actuator/**").permitAll()
			   .requestMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
			   .requestMatchers(HttpMethod.GET, "/v3/api-docs").permitAll()
			   .requestMatchers(HttpMethod.GET, "/webjars/**").permitAll()
			   .anyRequest().authenticated()
			   .and()
			   .addFilterBefore(this.securityFilter, UsernamePasswordAuthenticationFilter.class)
			   .build();
	}
	
	@Bean
	protected AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	protected PasswordEncoder passwordEncoder() throws Exception {
		return new BCryptPasswordEncoder();
	}
	
	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("thais1234"));
	}
}
