package com.project.BookMyShow.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.BookMyShow.Entity.Movie;
import com.project.BookMyShow.Service.MovieService;

@RestController
@RequestMapping("/api")
public class MovieController {
	
	private MovieService movieService;
	
	@Autowired
	public MovieController(MovieService movieService) {
		this.movieService = movieService;		
	}
	
	@GetMapping("/home/Movies-{city}")
	public List<Movie> getAllMoviesFromCity(@PathVariable("city") Long city){
		List<Movie> movies = movieService.getAllMovies(city);
		return movies;
	}

}
