package com.itvedant.movies.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.ManyToAny;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ElementCollection
	private List<String> seatNumber;
	private String bookingStatus;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@CreatedDate
	private LocalDateTime createdDate;
	@LastModifiedDate
	private LocalDateTime updatedDate;
	
	@ManyToOne
	@JoinColumn(name = "show_id")
	private movieShow show;
	
	@OneToOne(mappedBy = "bookings")
	private Payment payment;
	
	
	
	public movieShow getShow() {
		return show;
	}
	public void setShow(movieShow show) {
		this.show = show;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<String> getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(List<String> seatNumber) {
		this.seatNumber = seatNumber;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
}
