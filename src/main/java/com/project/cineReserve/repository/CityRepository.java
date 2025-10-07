package com.project.cineReserve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.cineReserve.entity.City;


public interface CityRepository extends JpaRepository<City, Long> {


    City findByCityName(String cityName);
}
