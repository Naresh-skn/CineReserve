package com.project.BookMyShow.Controller;

import com.project.BookMyShow.DTO.BookingRequestDTO;
import com.project.BookMyShow.Service.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {

	private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/booking/seats")
	public String bookSeats(@RequestBody BookingRequestDTO bookingRequestDTO){
        return bookingService.bookSeats(bookingRequestDTO);
	}
//
//	@PostMapping("/seats/payment")
//	public String payment(@RequestBody List<Long> seats) throws InterruptedException{
//        return seatService.payment(seats);
//	}
//
//



}
