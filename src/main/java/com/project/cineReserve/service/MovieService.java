package com.project.cineReserve.service;

import com.project.cineReserve.dto.MovieDTO;
import com.project.cineReserve.entity.Show;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

public interface MovieService {
    List<MovieDTO> getAllMovies();

    Map<String, List<Show>> getAllShows(Long cityId, Long movieId);

    MovieDTO addNewMovie(@Valid MovieDTO movieDTO);

    List<MovieDTO> getAllMoviesFromCity(Long cityId);


}
