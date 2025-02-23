package com.itvedant.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itvedant.movies.dao.ChangePasswordDAO;
import com.itvedant.movies.dao.CheckOtpDAO;
import com.itvedant.movies.dao.SendMailDAO;
import com.itvedant.movies.service.ForgetPasswordService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@Controller
@RequestMapping("/users")
public class ForgetPasswordController {

	@Autowired
	private ForgetPasswordService passwordService;
	
	
	
	@PostMapping("/email")
	public ResponseEntity<?> send(@RequestBody SendMailDAO sendMailDAO, HttpSession session) {
		
		session.setAttribute("email", sendMailDAO.getEmail());
		
		return ResponseEntity.ok(this.passwordService.SendEmail(sendMailDAO));
	}
	
	@PostMapping("/check")
	public ResponseEntity<?> check(@RequestBody CheckOtpDAO checkOtpDAO) {
		
		return ResponseEntity.ok(this.passwordService.checkOtp(checkOtpDAO));
	}
	
	@PutMapping("")
	public ResponseEntity<?> changePassword(HttpSession session, @RequestBody ChangePasswordDAO changePasswordDAO) {
		
		String email = (String) session.getAttribute("email");
		
		return ResponseEntity.ok(this.passwordService.updatePassword(email, changePasswordDAO));
	}
	
	
}
