package com.project.BookMyShow.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showSeatId;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    private String seatNumber;

    private LocalDateTime lockUntil;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "theater_seat_id")
//    @JsonIgnore
//    private TheaterSeat t;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.UNRESERVED;

    private Double price;

//    @ManyToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "booking_id")
//    private Booking booking;


}
