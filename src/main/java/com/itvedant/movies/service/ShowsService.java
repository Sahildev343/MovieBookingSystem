package com.itvedant.movies.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itvedant.movies.dao.AddShowDAO;
import com.itvedant.movies.entity.Movie;


import com.itvedant.movies.entity.Theater;
import com.itvedant.movies.entity.movieShow;
import com.itvedant.movies.repository.MoviesRepository;
import com.itvedant.movies.repository.ShowRepository;
import com.itvedant.movies.repository.TheaterRepository;


@Service
public class ShowsService {
	
	


	
	@Autowired
	private MoviesRepository moviesRepository;
	
	
	@Autowired
	private TheaterRepository theaterRepository;
	
	
	@Autowired
	private ShowRepository showRepository;
	
	
	public movieShow createShow(AddShowDAO addShowDAO) {
		
		Movie movie = this.moviesRepository.findById(addShowDAO.getMovieId()).orElseThrow( () -> new RuntimeException("Movie with this ID is not Found")); 
				
		
		Theater theater = this.theaterRepository.findById(addShowDAO.getTheaterId()).orElseThrow( () -> new RuntimeException("theater with this ID is not Found")); 
		
		movieShow shows = new movieShow();
		
		shows.setShowTime(addShowDAO.getShowTime());
		shows.setAvailableSeats(addShowDAO.getAvailableSeats());
		shows.setMovie(movie);   // ✅ Correct field name
		shows.setTheater(theater);   // ✅ Correct field name
		
		showRepository.save(shows);
		return shows;
		
	}
	
	
	
	
	
	

}
