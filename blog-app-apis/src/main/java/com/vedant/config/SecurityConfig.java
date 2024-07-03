package com.vedant.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableMethodSecurity(prePostEnabled = true) 
@EnableWebMvc


public class SecurityConfig {
	
	public static final String[] PUBLIC_URLS = 
		{"/auth/**",
			"/v3/api-docs",
			"/v2/api-docs","/swagger-resources/**",
			"/swagger-ui/**",
			"/webjars/**"};
	 
	@Autowired
	private JwtAuthenticationEntryPoint point;
	
	@Autowired
	private JwtAuthenticationFilter filter;
	
	@Autowired
	private UserDetailsService userdetailsservice;
	
	@Autowired
	private PasswordEncoder passwordencoder;
	
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		
		http.csrf(csrf->csrf.disable())
			.cors(cors->cors.disable())
			.authorizeHttpRequests(auth->auth.requestMatchers("/api/**")
			.authenticated()
			.requestMatchers(PUBLIC_URLS).permitAll()
			
			.requestMatchers(HttpMethod.GET).permitAll()
			.anyRequest().authenticated())
			
			.exceptionHandling(e->e.authenticationEntryPoint(point))
			.sessionManagement(se->se.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		
		
		
		return http.build();
	}
	
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(userdetailsservice);
		provider.setPasswordEncoder(passwordencoder);
		
		return provider;
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}
	
	
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		
//		auth.userDetailsService(this.userdetailsservice).passwordEncoder(passwordencoder);
//		
//	}
	
	
	
	

}
