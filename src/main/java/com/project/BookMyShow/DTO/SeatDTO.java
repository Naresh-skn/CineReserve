package com.project.BookMyShow.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.BookMyShow.Entity.Show;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatDTO {

    private Long seatId;

    private String seatNumber;

    private Boolean isBooked;

}
