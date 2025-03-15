package com.project.BookMyShow.Service;

import com.project.BookMyShow.DTO.MovieDTO;
import com.project.BookMyShow.DTO.ShowDTO;
import com.project.BookMyShow.Entity.Movie;
import com.project.BookMyShow.Entity.Seat;
import com.project.BookMyShow.Entity.Show;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

public interface MovieService {
    List<MovieDTO> getAllMovies();

    Map<String, List<Show>> getAllShows(Long cityId, Long movieId);

    List<Seat> getAllseats(Long showId);

    Show createShow(@Valid ShowDTO showDTO);

    MovieDTO addNewMovie(@Valid MovieDTO movieDTO);

    List<MovieDTO> getAllMoviesFromCity(Long cityId);

    List<ShowDTO> getAllShowsFromCityAndMovie(Long cityId, Long movieId);
}
