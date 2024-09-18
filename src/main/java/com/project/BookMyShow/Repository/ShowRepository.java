package com.project.BookMyShow.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.BookMyShow.Entity.Movie;
import com.project.BookMyShow.Entity.Show;

public interface ShowRepository extends JpaRepository<Show, Long>{
	
	@Query("Select Distinct s.movie from Show s where s.theatre.theatreId IN :theatreIds")
	Optional<List<Movie>> findByTheatreIds(List<Long> theatreIds);

	@Query("SELECT s FROM Show s WHERE s.theatre.theatreId IN :theatreIds and s.movie.movieId =:movie")
	Optional<List<Show>> findByTheatreIdsAndMovieId(List<Long> theatreIds, Long movie);

	
}
