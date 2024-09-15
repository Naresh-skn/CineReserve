package com.project.BookMyShow.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.BookMyShow.Entity.Movie;
import com.project.BookMyShow.Repository.ShowRepository;
import com.project.BookMyShow.Repository.TheatreRepository;
import com.project.BookMyShow.exception.GenException;

@Service
public class MovieService {

	
	private TheatreRepository theatreRepository;
	private ShowRepository showRepository;
	

	
	public MovieService(TheatreRepository theatreRepository, ShowRepository showRepository) {
		this.theatreRepository = theatreRepository;
		this.showRepository = showRepository;
	}
	
	public List<Movie> getAllMovies(Long city) {
		Optional<List<Long>> theatres = theatreRepository.findByCityId(city);
		if(theatres.get().size()==0)
			throw new GenException("No Theatres found in your City");
		List<Long> theatreList = theatres.get();
		
		Optional<List<Movie>> movies = showRepository.findByTheatreIds(theatreList);
		if(movies.get().size()==0)
			throw new GenException("No Movies found in your City");
		
		return movies.get();
	}
	
	

}
