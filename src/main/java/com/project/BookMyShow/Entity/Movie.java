package com.project.BookMyShow.Entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private int duration;  // Duration in minutes

    @Column(name = "language", nullable = false)
    private String language;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;
    
    @JsonIgnore
    @OneToMany(mappedBy = "movie")
    private List<Show> shows;
}
