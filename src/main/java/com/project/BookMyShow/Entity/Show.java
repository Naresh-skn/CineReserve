package com.project.BookMyShow.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "shows")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "show_id")
    private Long showId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    @ToString.Exclude
    private Movie movie;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "theatre_id", nullable = false)
    @ToString.Exclude
    private Theatre theatre;

    @Column(name = "show_time", nullable = false)
    private LocalDateTime showTime;


    @JsonIgnore
    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ShowSeat> seats = new ArrayList<>();

//    @JsonIgnore
//    @OneToMany(mappedBy = "show")
//    @ToString.Exclude
//    private List<Booking> bookings;
}
