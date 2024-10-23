package com.project.BookMyShow.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.BookMyShow.Entity.Movie;
import com.project.BookMyShow.Entity.Seat;
import com.project.BookMyShow.Entity.Show;
import com.project.BookMyShow.Repository.MovieRepository;
import com.project.BookMyShow.Repository.SeatRepository;
import com.project.BookMyShow.Repository.ShowRepository;
import com.project.BookMyShow.Repository.TheatreRepository;
import com.project.BookMyShow.exception.GenException;

@Service
public class MovieService {

	
	private TheatreRepository theatreRepository;
	private ShowRepository showRepository;
	private SeatRepository seatRepository;
	private MovieRepository movieRepository;


	public MovieService(TheatreRepository theatreRepository, ShowRepository showRepository,
			SeatRepository seatRepository, MovieRepository movieRepository) {
		this.theatreRepository = theatreRepository;
		this.showRepository = showRepository;
		this.seatRepository = seatRepository;
		this.movieRepository = movieRepository;
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
	

	public Map<String,List<Show>> getAllShows(Long city, Long movie) {
		Optional<List<Long>> theatres = theatreRepository.findByCityId(city);
		if(theatres.get().size()==0)
			throw new GenException("No Theatres found in your City");
		List<Long> theatreList = theatres.get();
		
		Optional<List<Show>> shows = showRepository.findByTheatreIdsAndMovieId(theatreList,movie);
		if(shows.get().size()==0)
			throw new GenException("No Movies found in your City");
		List<Show> showList = shows.get();
		
		Map<String,List<Show>> map = new HashMap<>();
		
		for(Show eachShow: showList) {
			String threatreName = eachShow.getTheatre().getName();
			if(map.containsKey(threatreName)) {
				List<Show> show = map.get(threatreName);
				show.add(eachShow);
				map.put(threatreName, show);	
			}
			else
				map.put(threatreName, new ArrayList<>(Arrays.asList(eachShow)));
			
		}
		return map;
	}

	public List<Seat> getAllseats(Long showId) {
		Optional<List<Seat>> seats = seatRepository.findByShowId(showId);
		if(seats.get().size()==0)
			throw new GenException("No Seats found");
		return seats.get();		
		
	}

	public Movie addNewMovie(Movie movie) {
		Movie movieFromDb = movieRepository.findByTitle(movie.getTitle());
		if(movieFromDb!=null) {
			throw new GenException("Movie Already Exists");
		}
		Movie savedMovie = movieRepository.save(movie);
		return savedMovie;
		
	}
	
	

}
