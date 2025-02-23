package com.itvedant.movies.entity;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Movie {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String title;
	private String genre;
	private String director;
	private String duration;
	private String moviePoster;
	
	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<movieShow> shows;  
    
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public List<movieShow> getShows() {
		return shows;
	}
	public void setShows(List<movieShow> shows) {
		this.shows = shows;
	}
	public void setMoviePoster(String moviePoster) {
		this.moviePoster = moviePoster;
	}
	
	
	
	public String getMoviePoster() {
		return moviePoster;
	}
	public void setMoviesPoster(String moviePoster) {
		this.moviePoster = moviePoster;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	
	
}
