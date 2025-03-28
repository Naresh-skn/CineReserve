package com.project.BookMyShow.Repository;

import com.project.BookMyShow.Entity.City;
import com.project.BookMyShow.Entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {

	List<Theatre> findByCity_CityId(Long cityId);

    List<Theatre> findByCity(City city);
}

