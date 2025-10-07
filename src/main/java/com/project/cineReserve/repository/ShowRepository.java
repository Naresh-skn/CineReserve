package com.project.cineReserve.repository;

import java.util.List;
import java.util.Optional;

import com.project.cineReserve.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.cineReserve.entity.Movie;
import com.project.cineReserve.entity.Show;
import org.springframework.data.repository.query.Param;

public interface ShowRepository extends JpaRepository<Show, Long>{


	@Query("SELECT s FROM Show s WHERE s.theatre.theatreId IN :theatreIds and s.movie.movieId =:movie")
	Optional<List<Show>> findByTheatreIdsAndMovieId(List<Long> theatreIds, Long movie);

	@Query("SELECT DISTINCT s.movie FROM Show s WHERE s.theatre IN :theatres")
	List<Movie> findDistinctMoviesByTheatreIn(List<Theatre> theatres);

		@Query("SELECT s FROM Show s " +
				"JOIN s.theatre t " +
				"WHERE s.movie.movieId = :movieId " +
				"AND t.city.cityId = :cityId " +
				"AND s.showTime > CURRENT_TIMESTAMP")
	List<Show> findShowsByMovieAndCity(@Param("movieId") Long movieId, @Param("cityId") Long cityId);
}
