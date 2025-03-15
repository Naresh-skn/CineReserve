package com.project.BookMyShow.Controller;

import com.project.BookMyShow.Service.SeatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SeatBookingController {
	
	private final SeatService seatService;

	public SeatBookingController(SeatService seatService) {
		this.seatService = seatService;
	}
	
	@PostMapping("/seats")
	public String bookSeats(@RequestBody List<Long> seats) throws InterruptedException{

        return seatService.bookMovieSeats(seats);
		
	}
	
	@PostMapping("/seats/payment")
	public String payment(@RequestBody List<Long> seats) throws InterruptedException{
        return seatService.payment(seats);
	}
	
	
	
	

}
