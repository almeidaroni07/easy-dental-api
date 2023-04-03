package rw.solution.easy.dental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import rw.solution.easy.dental.security.AuthenticationStateless;
import rw.solution.easy.dental.security.filter.AuthenticationFilter;
import rw.solution.easy.dental.service.AuthenticationService;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	
	@Autowired
	private AuthenticationStateless authenticationStateless;
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.authenticationStateless).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.authorizeRequests()
		.antMatchers("/assets/**").permitAll()
		.antMatchers("/swagger-ui.html").permitAll()
		.antMatchers("/actuator/**").permitAll()
		.antMatchers("/swagger-resources/**").permitAll()
		.antMatchers("/v2/api-docs/**").permitAll()
		.antMatchers("/webjars/**").permitAll()
		.antMatchers("/h2-console/**").permitAll()
		.antMatchers(HttpMethod.POST, "/auth/login").permitAll()
		.antMatchers(HttpMethod.GET, "/keepalive/status").permitAll()
		.anyRequest().authenticated();
		
		http.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilterBefore(new AuthenticationFilter(this.authenticationService), UsernamePasswordAuthenticationFilter.class);
		
		http.cors().and().csrf().disable();
		http.headers().cacheControl().disable();
		http.headers().frameOptions().sameOrigin();
		
	}
	
	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("thais1234"));
	}
}
