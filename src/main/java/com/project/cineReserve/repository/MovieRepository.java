package com.project.cineReserve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.cineReserve.entity.Movie;


public interface MovieRepository extends JpaRepository<Movie, Long> {

}
