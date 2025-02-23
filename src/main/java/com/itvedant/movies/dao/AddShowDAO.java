package com.itvedant.movies.dao;

public class AddShowDAO {

    private String showTime;
    private Integer availableSeats;
    private Integer movieId;  
    private Integer theaterId;

    // Getters and Setters
    public String getShowTime() {
        return showTime;
    }
    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }
    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Integer getMovieId() { 
        return movieId;
    }
    public void setMovieId(Integer movieId) {  
        this.movieId = movieId;
    }

    public Integer getTheaterId() {
        return theaterId;
    }
    public void setTheaterId(Integer theaterId) {
        this.theaterId = theaterId;
    }
}
