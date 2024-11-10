package com.project.BookMyShow.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.BookMyShow.DTO.ShowDTO;
import com.project.BookMyShow.DTO.TheatreDTO;
import com.project.BookMyShow.Entity.Movie;
import com.project.BookMyShow.Entity.Seat;
import com.project.BookMyShow.Entity.Show;
import com.project.BookMyShow.Entity.Theatre;
import com.project.BookMyShow.Service.MovieService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class MovieController {
	
	private MovieService movieService;
	
	@Autowired
	public MovieController(MovieService movieService) {
		this.movieService = movieService;		
	}
	
	
	@GetMapping("/home/Movies-{city}")
	public ResponseEntity<List<Movie>> getAllMoviesFromCity(@PathVariable("city") Long city){
		List<Movie> movies = movieService.getAllMovies(city);
		return ResponseEntity.status(HttpStatus.OK).body(movies);
	}
	
	
	@GetMapping("/buytickets/{movie}-{city}")
	public ResponseEntity<Map<String,List<Show>>> getAllShowsFromCityAndMovie(
			@PathVariable("city") Long city,
			@PathVariable("movie") Long movie){
				
		Map<String,List<Show>> shows = movieService.getAllShows(city,movie);
		return ResponseEntity.status(HttpStatus.OK).body(shows);//<>(shows,HttpStatus.OK);
	
		
	}
	
	
	@GetMapping("allSeats/{showId}")
	public ResponseEntity<List<Seat>> getSeatsfromShow(@PathVariable("showId") Long showId){
		List<Seat> seats = movieService.getAllseats(showId);
		return ResponseEntity.status(HttpStatus.OK).body(seats);
		
	}
	
	
	@PostMapping("admin/movie")
	public ResponseEntity<Movie> createNewMovie(@Valid @RequestBody Movie movie) {
		
		Movie savedMovie = movieService.addNewMovie(movie);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
	}
	
	@PostMapping("admin/theatre")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Theatre> createNewTheatre(@Valid @RequestBody TheatreDTO theatredto) {
		Theatre savedTheatre= movieService.createTheatre(theatredto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedTheatre);
	}
	
	@PostMapping("admin/show")
	public ResponseEntity<Show> createNewShow(@Valid @RequestBody ShowDTO showDTO) {
		Show savedshow= movieService.createShow(showDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedshow);
	}
	
	
	
	
	

}
