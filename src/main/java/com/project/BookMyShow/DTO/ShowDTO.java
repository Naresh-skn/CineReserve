package com.project.BookMyShow.DTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowDTO{

    private Long showId;
 
    private Long movieId;

    private Long theatreId;

    private LocalDateTime showTime;

    private Double price;

}
