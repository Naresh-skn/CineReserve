package com.project.BookMyShow.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.project.BookMyShow.Entity.Seat;

import jakarta.persistence.LockModeType;


public interface SeatRepository extends JpaRepository<Seat, Long> {
	//@Query("SELECT s FROM Seat s WHERE s.booked = false AND (s.lockedUntil IS NULL OR s.lockedUntil < :now)")
	@Query("SELECT s FROM Seat s WHERE s.show.showId =:showId and s.isBooked = false AND (s.lockedUntil IS NULL OR s.lockedUntil < CURRENT_TIMESTAMP)")
	Optional<List<Seat>> findByShowId(Long showId);
	
	 @Lock(LockModeType.PESSIMISTIC_WRITE)
	 @Query("SELECT s FROM Seat s WHERE s.id IN :seatIds AND (s.lockedUntil IS NULL OR s.lockedUntil < CURRENT_TIMESTAMP)")
	 List<Seat> findAvailableSeatsWithLock(List<Long> seatIds);
	 
	 @Query("SELECT s FROM Seat s WHERE s.id IN :seatIds AND (s.lockedUntil IS NOT NULL OR s.lockedUntil < CURRENT_TIMESTAMP) AND s.isBooked = false")
	 List<Seat> findAvailableSeats(List<Long> seatIds);

}
