package com.itvedant.movies.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itvedant.movies.dao.AddBookingDAO;
import com.itvedant.movies.entity.Booking;

import com.itvedant.movies.entity.User;
import com.itvedant.movies.entity.movieShow;
import com.itvedant.movies.repository.BookingRepository;
import com.itvedant.movies.repository.ShowRepository;
import com.itvedant.movies.repository.UserRepository;

@Service
public class BookingService {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ShowRepository showRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	
	
	public List<Booking> getBookingByUser(Integer id) {
		List<Booking> list = new ArrayList<Booking>();
		
		list = this.bookingRepository.findAll();
		
		return list;
	}
	
	
	public Booking create(AddBookingDAO addBookingDAO) {
		
		User user = this.userRepository.findById(addBookingDAO.getUserId()).orElse(null);
		
		movieShow shows = this.showRepository.findById(addBookingDAO.getShowId()).orElseThrow(() -> new RuntimeException("Show not found with ID: " + addBookingDAO.getShowId()));
		
		
		int seatsToBook = addBookingDAO.getSeatNumber().size();
		if(shows.getAvailableSeats() < seatsToBook) {
			throw new  RuntimeException("Only " + shows.getAvailableSeats() + " seats left. Cannot Book " + seatsToBook);
		}
		
		shows.setAvailableSeats(shows.getAvailableSeats() - seatsToBook);
		this.showRepository.save(shows);
		
		
		Booking book = new Booking();
		
	    book.setSeatNumber(addBookingDAO.getSeatNumber());
	    book.setBookingStatus(addBookingDAO.getBookingStatus());
	    book.setUser(user);
	    book.setShow(shows);
	    
	    bookingRepository.save(book);
	    
	    return book;
	}
	
        public String CancelBooking(Integer bookingId) {
		
		Optional<Booking> bookingoptional = this.bookingRepository.findById(bookingId);
		
		if(bookingoptional.isEmpty()) {
			throw new RuntimeException("Booking not found with this ID : " + bookingId);
		}
		
		Booking booking = bookingoptional.get();
		movieShow shows = booking.getShow();
		int bookedSeat = booking.getSeatNumber().size();
		
		
		//Increase Available Seat
		shows.setAvailableSeats(shows.getAvailableSeats() + bookedSeat);
		this.showRepository.save(shows);
		
		//DeleteBooking
		
		this.bookingRepository.delete(booking);
		
		return "✅ Booking Canceled Successfully " + bookedSeat + " seats restored";
		
		
	}
	
	
	
	
}
