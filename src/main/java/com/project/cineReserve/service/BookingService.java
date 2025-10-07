package com.project.cineReserve.service;

import com.project.cineReserve.dto.BookingRequestDTO;

public interface BookingService {

    String bookSeats(BookingRequestDTO bookingRequestDTO);

    String confirmBooking(Long bookingId);

    String cancelBooking(Long bookingId);
}



