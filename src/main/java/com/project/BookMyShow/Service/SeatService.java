package com.project.BookMyShow.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.project.BookMyShow.Entity.Movie;
import com.project.BookMyShow.Entity.Show;
import com.project.BookMyShow.Entity.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project.BookMyShow.Entity.Seat;
import com.project.BookMyShow.Repository.SeatRepository;
import com.project.BookMyShow.exception.GenException;

import jakarta.transaction.Transactional;

@Service
public class SeatService {
	
	private final SeatRepository seatRepository;


	
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
        Thread.sleep(60000);
		sendSimpleEmail(seats);


      
		return "Tset";

		
	}

	public void sendSimpleEmail(List<Seat> seats) {
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setTo("nnnaresh26@gmail.com");
//		StringBuilder body = new StringBuilder();
//		Seat first = seats.getFirst();
//		Theatre theatre = first.getTheatre();
//		Movie movie = first.getShow().getMovie();
//		Show show = first.getShow();
//		body.append("Theatre Name: ").append(theatre.getTheatreName()).append("\n");
//		body.append("Movie:- ").append(movie.getTitle()).append("\n");
//		body.append("ShowTime:- ").append(show.getShowTime()).append("\n");
//		for (Seat seat: seats){
//			body.append(seat.getSeatId()).append(" \n");
//		}
//		System.out.println(body);
//		message.setSubject("Testing mail");
//		message.setText("Hello, How are you");
//		message.setFrom("person.notknown.oo1@gmail.com"); // Optional, but recommended
//		mailSender.send(message);
	}
	
	
	@Transactional
	public String payment(List<Long> seatIds) {
	
		List<Seat> seats = seatRepository.findAvailableSeats(seatIds);
		  try {
	            // Proceed with payment
	      
	            // Confirm the booking for all seats
				for (Seat seat : seats) {
				    seat.setIsBooked(Boolean.TRUE);
				    seat.setLockedUntil(null);  // Clear the lock
				}
				
	        } finally {
	            // If payment failed or any exception occurred, release all locks after the timeout automatically
	            if (!seats.stream().allMatch(Seat::getIsBooked)) {
	                for (Seat seat : seats) {
	                	seat.setIsBooked(Boolean.FALSE);
	                	seat.setLockedUntil(null);
	                }
	                }
	                seatRepository.saveAll(seats);
	            }
		return "Success";
	}

}
