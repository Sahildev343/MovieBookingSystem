package com.itvedant.movies.dao;

import java.util.List;

public class AddBookingDAO {

	private List<String> seatNumber;
	private String bookingStatus;
	private Integer userId;
	private Integer showId;
	
	
	public List<String> getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(List<String> seatNumber) {
		this.seatNumber = seatNumber;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getShowId() {
		return showId;
	}
	public void setShowId(Integer showId) {
		this.showId = showId;
	}
	
	
}
