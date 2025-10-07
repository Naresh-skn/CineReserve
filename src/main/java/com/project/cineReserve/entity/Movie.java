package com.project.cineReserve.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "language", nullable = false)
    private String language;

    @Column(name = "rating")
    private Double rating;

    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;
    
//    @JsonIgnore
//    @OneToMany(mappedBy = "movie")
//    private List<Show> shows = new ArrayList<>();
}
