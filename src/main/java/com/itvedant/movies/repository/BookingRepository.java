package com.itvedant.movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itvedant.movies.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

	List<Booking> findByUserId(Integer userId);
}
