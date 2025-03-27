package com.project.BookMyShow.Service;

import com.project.BookMyShow.DTO.BookingRequestDTO;
import com.project.BookMyShow.Entity.*;
import com.project.BookMyShow.Repository.BookingRepository;
import com.project.BookMyShow.Repository.ShowRepository;
import com.project.BookMyShow.Repository.ShowSeatRepository;
import com.project.BookMyShow.Repository.UserRepository;
import com.project.BookMyShow.exception.GenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements  BookingService{

    private final BookingRepository bookingRepository;

    private final ShowSeatRepository showSeatRepository;

    private final ShowRepository showRepository;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public String bookSeats(BookingRequestDTO bookingRequestDTO) {
        User user = userRepository.findById(1L)
                .orElseThrow(()->new GenException("No user Found"));
        List<ShowSeat> availableSeats = showSeatRepository
                .findAvailableSeats(bookingRequestDTO.getSeatIds());
        Show show = showRepository.findById(bookingRequestDTO.getShowId())
                .orElseThrow(()->new GenException("Show Not Found"));

        if(availableSeats.size()!=bookingRequestDTO.getSeatIds().size())
            throw new GenException("Seats Not available");

        Double totalPrice = 0D;

        Instant lockExpiration = Instant.now().plus(5, ChronoUnit.MINUTES);
        for(ShowSeat showSeat : availableSeats){
            showSeat.setLockUntil(LocalDateTime.from(lockExpiration));
            totalPrice+=showSeat.getPrice();
        }
        showSeatRepository.saveAll(availableSeats);

        // Create a new booking entry with 'PENDING' status
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setBookingTime(LocalDateTime.now());
        booking.setBookingStatus(BookingStatus.RESERVED_PAYMENT_PENDING);
        booking.setSeats(new HashSet<>(availableSeats));
        booking.setTotalPrice(totalPrice);
        bookingRepository.save(booking);

        return "Seats locked successfully! Complete payment within 5 minutes.";

    }
}
