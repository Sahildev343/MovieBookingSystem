package com.itvedant.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itvedant.movies.dao.AddPayment;
import com.itvedant.movies.service.PaymentService;

@Controller
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	
	
	@PostMapping("")
	public ResponseEntity<?> create(AddPayment addPayment) {
		return ResponseEntity.ok(this.paymentService.create(addPayment));
	}
	
}
