package com.project.cineReserve.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseShowDTO{

    private Long showId;

    private MovieDTO movie;

    private TheatreDTO theatre;

    private LocalDateTime showTime;

}