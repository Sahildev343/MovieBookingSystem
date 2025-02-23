package com.itvedant.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itvedant.movies.entity.Movie;





@Repository
public interface MoviesRepository extends JpaRepository<Movie, Integer> {

}
