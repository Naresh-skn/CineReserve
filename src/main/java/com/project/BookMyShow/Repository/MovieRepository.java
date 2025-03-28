package com.project.BookMyShow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.BookMyShow.Entity.Movie;


public interface MovieRepository extends JpaRepository<Movie, Long> {

}
