package com.notesapp.notesapp.config;

import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	 @Autowired
	 private JwtAuthFilter authFilter;
	 
	 @Bean
	 public UserDetailsService userDetailsService() {		 
	    return new UserInfoUserDetailService();
	}
		 
	 @Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
				/*
				 * return http.csrf().disable() .authorizeHttpRequests()
				 * .requestMatchers("/loginPage","/saveNotes","/getUsers").permitAll() .and()
				 * .authorizeHttpRequests().requestMatchers("/products/**")
				 * .authenticated().and() .sessionManagement()
				 * .sessionCreationPolicy(SessionCreationPolicy.STATELESS) .and()
				 * .authenticationProvider(authenticationProvider())
				 * .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
				 * .build();
				 */			
				
		 return
			http.csrf().disable().authorizeRequests().requestMatchers("/user/**","/createUser").permitAll()
			.requestMatchers("/auth/**").authenticated()
			.and().formLogin().loginPage("/loginPage")
			.loginProcessingUrl("/authenticate").permitAll(). and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS) .and()
			.authenticationProvider(authenticationProvider())
			.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
			.build();

	 }	 
	
	 //password encoder
	  @Bean
	  public PasswordEncoder passwordEncoder() {
	      return new BCryptPasswordEncoder();
	  }
	  
	  //authentication provider this will give types of authetication
	  @Bean
	  public AuthenticationProvider authenticationProvider(){
		  DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
	      authenticationProvider.setUserDetailsService(userDetailsService());
	      authenticationProvider.setPasswordEncoder(passwordEncoder());
	      return authenticationProvider;
	    }
	  
	 @Bean
	 public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	    return config.getAuthenticationManager();
	 }
}
