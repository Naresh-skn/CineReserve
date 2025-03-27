package com.project.BookMyShow.Repository;

import com.project.BookMyShow.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookingRepository extends JpaRepository<Booking, Long> {

}
