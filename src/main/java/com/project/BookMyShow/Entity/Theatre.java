package com.project.BookMyShow.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theatres")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theatre_id")
    private Long theatreId;
    
    
    @Column(name="capacity")
    private Integer capacity;

    @Column(name = "name", nullable = false)
    private String theatreName;

    @Column(name = "address", nullable = false)
    private String address;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    @ToString.Exclude
    private City city;
 
    @JsonIgnore
    @OneToMany(mappedBy = "theatre")
    @ToString.Exclude
    private List<Show> shows = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SeatTypeConfiguration> seatConfigurations = new ArrayList<>();


}
