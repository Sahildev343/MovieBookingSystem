package com.itvedant.movies.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.itvedant.movies.dao.ChangePasswordDAO;
import com.itvedant.movies.dao.CheckOtpDAO;
import com.itvedant.movies.dao.SendMailDAO;
import com.itvedant.movies.entity.User;
import com.itvedant.movies.repository.UserRepository;

@Service
public class ForgetPasswordService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	Random random = new Random();
	
	int otp = 0;
	public String SendEmail(SendMailDAO sendMailDAO) {
		
		if(userRepository.findByEmail(sendMailDAO.getEmail()).isPresent()) {
			
			SimpleMailMessage message = new SimpleMailMessage();
			this.otp = random.nextInt(1000);
			message.setFrom("sahiluradi567@gmail.com");
			message.setTo(sendMailDAO.getEmail());
			message.setSubject("OTP Verfication");
			message.setText("Your OTP is " + this.otp);
			
			mailSender.send(message);
			
			return "Mail Send";
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email is Not Registered");
		}
		
	}
	
	public String checkOtp(CheckOtpDAO checkOtpDAO) {
		
		if(this.otp == checkOtpDAO.getOtp()) {
			return "OTP Matched";
		}else {
			return "OTP Does Not Matched";
		}
	}
	
	public User updatePassword(String email, ChangePasswordDAO changePasswordDAO) {
		
		User user = this.userRepository.findByEmail(email).orElse(null);
		
		if(changePasswordDAO.getNewPassword().equals(changePasswordDAO.getConfirmPassword())) {
			
			user.setPassword(bCryptPasswordEncoder.encode(changePasswordDAO.getNewPassword()));
			
			this.userRepository.save(user);
			
			return user;
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password And Confirm does not Matched");
		}
	}

	

}
