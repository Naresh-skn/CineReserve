package com.project.cineReserve.controller;

import com.project.cineReserve.dto.MovieDTO;
import com.project.cineReserve.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MovieController {
	
	private final MovieService movieService;

	@PostMapping("admin/movie")
	public ResponseEntity<MovieDTO> createNewMovie(@Valid @RequestBody MovieDTO movieDTO) {
		MovieDTO savedMovie = movieService.addNewMovie(movieDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
	}

	@GetMapping("/home/movies/city/{cityId}")
	public ResponseEntity<List<MovieDTO>> getAllMoviesFromCity(@PathVariable("cityId") Long cityId){
		List<MovieDTO> movies = movieService.getAllMoviesFromCity(cityId);
		return ResponseEntity.status(HttpStatus.OK).body(movies);
	}


	@GetMapping("public/movies")
	public ResponseEntity<List<MovieDTO>> getAllMovies() {
		List<MovieDTO> MoviesDTO = movieService.getAllMovies();
		return ResponseEntity.status(HttpStatus.CREATED).body(MoviesDTO);
	}

}
