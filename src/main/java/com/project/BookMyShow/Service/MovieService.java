package com.project.BookMyShow.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.BookMyShow.DTO.ShowDTO;
import com.project.BookMyShow.DTO.TheatreDTO;
import com.project.BookMyShow.Entity.City;
import com.project.BookMyShow.Entity.Movie;
import com.project.BookMyShow.Entity.Seat;
import com.project.BookMyShow.Entity.Show;
import com.project.BookMyShow.Entity.Theatre;
import com.project.BookMyShow.Repository.CityRepository;
import com.project.BookMyShow.Repository.MovieRepository;
import com.project.BookMyShow.Repository.SeatRepository;
import com.project.BookMyShow.Repository.ShowRepository;
import com.project.BookMyShow.Repository.TheatreRepository;
import com.project.BookMyShow.exception.GenException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class MovieService {

	
	private final TheatreRepository theatreRepository;
	private final ShowRepository showRepository;
	private final SeatRepository seatRepository;
	private final MovieRepository movieRepository;
	
	@Autowired
	private CityRepository cityRepository;


	public MovieService(TheatreRepository theatreRepository, ShowRepository showRepository,
			SeatRepository seatRepository, MovieRepository movieRepository) {
		this.theatreRepository = theatreRepository;
		this.showRepository = showRepository;
		this.seatRepository = seatRepository;
		this.movieRepository = movieRepository;
	}


	public List<Movie> getAllMovies(Long cityId) {
		Optional<List<Long>> theatres = theatreRepository.findByCityId(cityId);
		if(theatres.isEmpty())
			throw new GenException("No Theatres found in your City");
		List<Long> theatreList = theatres.get();	
		Optional<List<Movie>> movies = showRepository.findByTheatreIds(theatreList);
		if(movies.get().size()==0)
			throw new GenException("No Movies found in your City");
		return movies.get();
	}
	

	public Map<String,List<Show>> getAllShows(Long city, Long movie) {
		Optional<List<Long>> theatres = theatreRepository.findByCityId(city);
		if(theatres.get().isEmpty())
			throw new GenException("No Theatres found in your City");
		List<Long> theatreList = theatres.get();
		
		Optional<List<Show>> shows = showRepository.findByTheatreIdsAndMovieId(theatreList,movie);
		if(shows.get().size()==0)
			throw new GenException("No Movies found in your City");
		List<Show> showList = shows.get();
		
		Map<String,List<Show>> map = new HashMap<>();
		
		for(Show eachShow: showList) {
			String threatreName = eachShow.getTheatre().getTheatreName();
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
        return movieRepository.save(movie);
		
	}


	public Theatre createTheatre(@Valid TheatreDTO theatredto) {
		
		return null;
	}


	public List<Theatre> getthea() {
		// TODO Auto-generated method stub
		return theatreRepository.findAll();
	}


	@Transactional
	public Show createShow(@Valid ShowDTO showDTO) {
Optional<Theatre> theatre = theatreRepository.findById(showDTO.getTheatreId());
	
Optional<Movie> movie = movieRepository.findById(showDTO.getMovieId());
		
		Show show= Show.builder()
				.movie(movie.get())
				.price(showDTO.getPrice())
				.theatre(theatre.get())
				.showTime(showDTO.getShowTime())
				.build();
		List<Seat> seats = new ArrayList<>();
		for(int i=0;i<theatre.get().getCapacity();i++) {
			Seat seat = Seat.builder()
					.theatre(theatre.get())
					.seatNumber("s"+i)
					.show(show)
					.isBooked(Boolean.FALSE)
					.build();
			seats.add(seat);
			
		}
		Show Savedshow= showRepository.save(show);		
		List<Seat> savedseats = seatRepository.saveAll(seats);
		System.out.println(savedseats);
		return Savedshow;
	}
	
	

}
