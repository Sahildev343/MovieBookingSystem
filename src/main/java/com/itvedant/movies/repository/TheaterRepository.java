package com.itvedant.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itvedant.movies.entity.Theater;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Integer> {

}
