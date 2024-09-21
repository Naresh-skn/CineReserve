package com.project.BookMyShow.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowResponse {

    private Long showId;

    private Long movieId;

    private Long theatreId;

    private LocalDateTime showTime;

    private Double price;

}
