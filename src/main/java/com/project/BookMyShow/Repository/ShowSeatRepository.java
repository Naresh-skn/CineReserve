package com.project.BookMyShow.Repository;

import com.project.BookMyShow.Entity.ShowSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    List<ShowSeat> findByShow_ShowId(Long showId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM ShowSeat s WHERE s.showSeatId IN :seatIds AND " +
            "(s.bookingStatus = com.project.BookMyShow.Entity.BookingStatus.UNRESERVED OR " +
            "(s.bookingStatus = com.project.BookMyShow.Entity.BookingStatus.RESERVED_PAYMENT_PENDING" +
            " AND s.lockUntil < CURRENT_TIMESTAMP))")
    List<ShowSeat> findAvailableSeats(List<Long> seatIds);
}
