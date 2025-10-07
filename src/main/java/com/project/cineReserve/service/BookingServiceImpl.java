package com.project.cineReserve.service;

import com.project.cineReserve.dto.BookingRequestDTO;
import com.project.cineReserve.entity.*;
import com.project.cineReserve.repository.BookingRepository;
import com.project.cineReserve.repository.ShowRepository;
import com.project.cineReserve.repository.ShowSeatRepository;
import com.project.cineReserve.repository.UserRepository;
import com.project.cineReserve.exception.GenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Show show = showRepository.findById(bookingRequestDTO.getShowId())
                .orElseThrow(()->new GenException("Show Not Found"));
        List<ShowSeat> availableSeats = showSeatRepository
                .findAvailableSeats(bookingRequestDTO.getSeatIds());


        if(availableSeats.size()!=bookingRequestDTO.getSeatIds().size())
            throw new GenException("Seats Not available");

        Double totalPrice = 0D;

        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(5);
        for(ShowSeat showSeat : availableSeats){
            showSeat.setLockUntil(localDateTime);
            totalPrice+=showSeat.getPrice();
            showSeat.setBookingStatus(BookingStatus.RESERVED_PAYMENT_PENDING);
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

    @Override
    @Transactional
    public String confirmBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(()->new GenException("No Booking Found"));
        Set<ShowSeat> seats = booking.getSeats();
        for(ShowSeat showSeat : seats){
            showSeat.setLockUntil(null);
            showSeat.setBookingStatus(BookingStatus.CONFIRMED);
        }
        showSeatRepository.saveAll(seats);
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        bookingRepository.save(booking);
        return "Ticket Confirmed";
    }

    @Override
    public String cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(()->new GenException("No Booking Found"));

        if(!booking.getBookingStatus().equals(BookingStatus.RESERVED_PAYMENT_PENDING))
            throw new GenException("Booking can not cancelled");

        Set<ShowSeat> seats = booking.getSeats();
        for(ShowSeat showSeat : seats){
            showSeat.setLockUntil(null);
            showSeat.setBookingStatus(BookingStatus.UNRESERVED);
        }
        showSeatRepository.saveAll(seats);
        booking.setBookingStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
        return "Booking cancelled successfully";
    }


}
