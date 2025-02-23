package com.itvedant.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itvedant.movies.dao.AddPayment;
import com.itvedant.movies.entity.Booking;
import com.itvedant.movies.entity.Payment;
import com.itvedant.movies.repository.BookingRepository;
import com.itvedant.movies.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	
	
	public Payment create(AddPayment addPayment) {
		
		Booking booking = this.bookingRepository.findById(addPayment.getBookingId()).orElse(null);
		
		Payment payment = new Payment();
		payment.setBookings(booking);
		payment.setPaymentStatus(addPayment.getPaymentStatus());
		payment.setTransactionId(addPayment.getTransactionId());
		
		paymentRepository.save(payment);
		
		return payment;
		
		
	}
}
