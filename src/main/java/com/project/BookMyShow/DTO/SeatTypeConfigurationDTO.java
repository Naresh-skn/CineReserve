package com.project.BookMyShow.DTO;


import com.project.BookMyShow.Entity.SeatType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SeatTypeConfigurationDTO {

    @NotNull(message = "Seat type is required")
    private SeatType seatType;

    @Min(value = 1, message = "Seat count must be at least 1")
    private int seatCount;

    @Min(value = 1, message = "Price must be greater than zero")
    private double price;
}