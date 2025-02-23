package com.itvedant.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.itvedant.movies.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
		    .authorizeHttpRequests()
		    .requestMatchers(HttpMethod.GET,"/movieses/**").permitAll()
		    .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
		    .anyRequest().permitAll()
		    .and()
		    .httpBasic(Customizer.withDefaults())
		    .authenticationProvider(daoAuthenticationProvider());
		
		return http.build();
		    
		    
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService users() {
		
		UserDetails user = User.builder()
				               .username("user")
				               .password(passwordEncoder().encode("password"))
				               .roles("USER")
				               .build();
		
		UserDetails admin = User.builder()
				                .username("admin")
				                .password(passwordEncoder().encode("password"))
				                .roles("ADMIN")
				                .build();
		
		return new InMemoryUserDetailsManager(user,admin);
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(this.myUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		
		return configuration.getAuthenticationManager();
	}
	
	
 
}
