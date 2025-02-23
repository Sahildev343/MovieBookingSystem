package com.itvedant.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.itvedant.movies.entity.movieShow;



@Repository
public interface ShowRepository extends JpaRepository<movieShow, Integer>{

}
