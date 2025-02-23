package com.itvedant.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itvedant.movies.dao.AddBookingDAO;
import com.itvedant.movies.service.BookingService;

import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/bookings")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	
	
	    @GetMapping("/{userId}")
	    public ResponseEntity<?> getUserBookings(@PathVariable Integer userId) {
	        return ResponseEntity.ok(this.bookingService.getBookingByUser(userId));
	    }
	
	@PostMapping("/{id}/users")
	public ResponseEntity<?> create(@RequestBody AddBookingDAO addBookingDAO) {
		
		return ResponseEntity.ok(this.bookingService.create(addBookingDAO));
		
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> cancel(@PathVariable Integer id) {
		
		return ResponseEntity.ok(this.bookingService.CancelBooking(id));
	}
	
}
