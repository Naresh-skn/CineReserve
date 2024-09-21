package com.project.BookMyShow.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.BookMyShow.Entity.Seat;


public interface SeatRepository extends JpaRepository<Seat, Long> {

	@Query("SELECT s FROM Seat s WHERE s.show.showId =:showId")
	Optional<List<Seat>> findByShowId(Long showId);

}
