package com.project.cineReserve.controller;

import com.project.cineReserve.dto.BookingRequestDTO;
import com.project.cineReserve.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/test/booking/seats")
    public String testBooking(@RequestBody BookingRequestDTO bookingRequestDTO) throws InterruptedException {
            Thread th1 = new Thread(() -> {
                try {
                    List<Long> seats= new ArrayList<>();
                    seats.add(10L);
                    seats.add(11L);
                    BookingRequestDTO bookingRequestDTO1 = new BookingRequestDTO(1L,seats);
                    System.out.println(Thread.currentThread().getName() + " is attempting to book the seat");
                    String st= bookingService.bookSeats(bookingRequestDTO1);
                    System.out.println(Thread.currentThread().getName() + " successfully booked the seat with version ");
                } catch (Exception ex) {
                    System.out.println(Thread.currentThread().getName() + " failed : " + ex.getMessage());
                }
            });

            Thread th2 = new Thread(() -> {
                try {

                    List<Long> seats= new ArrayList<>();
                    seats.add(10L);
                    seats.add(12L);
                    BookingRequestDTO bookingRequestDTO1 = new BookingRequestDTO(1L,seats);
                    System.out.println(Thread.currentThread().getName() + " is attempting to book the seat");
                    String st= bookingService.bookSeats(bookingRequestDTO1);
                    System.out.println(Thread.currentThread().getName() + " successfully booked the seat with version ");
                } catch (Exception ex) {
                    System.out.println(Thread.currentThread().getName() + " failed : " + ex.getMessage());
                }
            });

            Thread th3 = new Thread(() -> {
                try {

                    List<Long> seats= new ArrayList<>();
                    seats.add(10L);
                    seats.add(15L);
                    seats.add(16L);
                    BookingRequestDTO bookingRequestDTO1 = new BookingRequestDTO(1L,seats);

                    bookingRequestDTO.getSeatIds().add(14L);
                    System.out.println(Thread.currentThread().getName() + " is attempting to book the seat");
                    String st= bookingService.bookSeats(bookingRequestDTO1);
                    System.out.println(Thread.currentThread().getName() + " successfully booked the seat with version ");
                } catch (Exception ex) {
                    System.out.println(Thread.currentThread().getName() + " failed : " + ex.getMessage());
                }
            });

            th1.start();
            th2.start();
            th3.start();
            th1.join();
            th2.join();
            th3.join();
            return "Test Done";
        }

    @PostMapping("/booking/confirm-seats")
    public String confirmBooking(@RequestParam Long bookingId){
        return bookingService.confirmBooking(bookingId);
    }

    @PostMapping("/booking/cancel-seats")
    public String cancelBooking(@RequestParam Long bookingId){
        return bookingService.cancelBooking(bookingId);
    }

}
