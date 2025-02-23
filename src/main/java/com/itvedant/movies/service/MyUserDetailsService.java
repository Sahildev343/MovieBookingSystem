package com.itvedant.movies.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itvedant.movies.repository.UserRepository;

import jakarta.websocket.server.ServerEndpoint;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return this.userRepository
				.findByEmail(username)
				.map( user -> {
					return new User(
							user.getEmail(),
							user.getPassword(),
							user.getRole().stream()
							.map( role -> new SimpleGrantedAuthority(role))
							.collect(Collectors.toList())
							
							
							
							);
							
				}).orElseThrow(() -> {
					throw new UsernameNotFoundException("User with email does not exist");
				});
		
	}
}
