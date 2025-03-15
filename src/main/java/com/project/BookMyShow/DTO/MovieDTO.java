package com.project.BookMyShow.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    private Long movieId;
    @NotBlank
    private String title;

    private String genre;

    private int duration;

    private String language;

    private Double rating;

    private String description;

    private LocalDate releaseDate;

}
