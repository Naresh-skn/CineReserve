package com.project.BookMyShow.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.BookMyShow.Entity.Movie;
import com.project.BookMyShow.Repository.ShowRepository;
import com.project.BookMyShow.Repository.TheatreRepository;

@Service
public class MovieService {

	
	private TheatreRepository theatreRepository;
	private ShowRepository showRepository;
	
	
	
	
	public MovieService(TheatreRepository theatreRepository, ShowRepository showRepository) {
		this.theatreRepository = theatreRepository;
		this.showRepository = showRepository;
	}




	public List<Movie> getAllMovies(Long city) {
		List<Long> theatres = theatreRepository.findByCityId(city);
		List<Movie> movies = showRepository.findByTheatreIds(theatres);
		return movies;
	}
	
	

}
