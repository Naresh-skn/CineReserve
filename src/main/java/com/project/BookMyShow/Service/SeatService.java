package com.project.BookMyShow.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.BookMyShow.Entity.Seat;
import com.project.BookMyShow.Repository.SeatRepository;
import com.project.BookMyShow.exception.GenException;

import jakarta.transaction.Transactional;

@Service
public class SeatService {
	
	private SeatRepository seatRepository;
	
	public SeatService(SeatRepository seatRepository) {
		this.seatRepository = seatRepository;
	}

	@Transactional
	public String bookMovieSeats(List<Long> seatIds) throws InterruptedException {
		
		
		List<Seat> seats = seatRepository.findAvailableSeatsWithLock(seatIds);
		
		if (seats.size() != seatIds.size()) {
            throw new GenException("One or more seats are currently locked or already booked");
        }

        // Temporarily lock the seats by setting the lockedUntil field
        LocalDateTime lockExpirationTime = LocalDateTime.now().plusMinutes(5);
        for (Seat seat : seats) {
            seat.setLockedUntil(lockExpirationTime);
        }
        seatRepository.saveAll(seats);  // Save locked seats
        
      
		return "Tset";

		
	}
	
	
	@Transactional
	public String payment(List<Long> seatIds) {
	
		List<Seat> seats = seatRepository.findAvailableSeats(seatIds);
		  try {
	            // Proceed with payment
	      
	            // Confirm the booking for all seats
				for (Seat seat : seats) {
				    seat.setIsBooked(true);
				    seat.setLockedUntil(null);  // Clear the lock
				}
				
	        } finally {
	            // If payment failed or any exception occurred, release all locks after the timeout automatically
	            if (!seats.stream().allMatch(Seat::getIsBooked)) {
	                for (Seat seat : seats) {
	                	seat.setIsBooked(false);
	                	seat.setLockedUntil(null);
	                }
	                }
	                seatRepository.saveAll(seats);
	            }
		return "Success";
	}

}
