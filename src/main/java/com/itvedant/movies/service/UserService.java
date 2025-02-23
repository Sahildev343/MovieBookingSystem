package com.itvedant.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.itvedant.gamestop.entity.Product;
import com.itvedant.movies.dao.RegisterUserDAO;
import com.itvedant.movies.entity.User;
import com.itvedant.movies.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public User Register(RegisterUserDAO registerUserDAO) {
		if(this.userRepository.findByEmail(registerUserDAO.getEmail()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this Email Already Registered");
		}
		
		User user = new User();
		
		user.setName(registerUserDAO.getName());
		user.setEmail(registerUserDAO.getEmail());
		user.setPassword(passwordEncoder.encode(registerUserDAO.getPassword()));
		user.setMobile_no(registerUserDAO.getMobile_no());
		user.setRole(registerUserDAO.getRole());
		
		this.userRepository.save(user);
		
		return user;
	}
	
public User findById(Integer id) {
		
		User user = new User();
		
		user = this.userRepository.findById(id).orElse(null);
		
		return user;
	}
	
	
	public String delete(Integer id) {
		
		User user = new User();
		
		user = this.findById(id);
		
		this.userRepository.delete(user);
		
		return "delete user successfully";
		
	}

	
}
