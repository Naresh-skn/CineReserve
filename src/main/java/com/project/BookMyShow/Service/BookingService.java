package com.project.BookMyShow.Service;

import com.project.BookMyShow.DTO.BookingRequestDTO;

public interface BookingService {

    String bookSeats(BookingRequestDTO bookingRequestDTO);

    String confirmBooking(Long bookingId);

    String cancelBooking(Long bookingId);
}



