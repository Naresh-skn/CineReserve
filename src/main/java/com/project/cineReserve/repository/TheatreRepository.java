package com.project.cineReserve.repository;

import com.project.cineReserve.entity.City;
import com.project.cineReserve.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {

	List<Theatre> findByCity_CityId(Long cityId);

    List<Theatre> findByCity(City city);
}

