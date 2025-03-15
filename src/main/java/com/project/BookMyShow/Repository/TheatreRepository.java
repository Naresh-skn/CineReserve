package com.project.BookMyShow.Repository;

import java.util.List;
import java.util.Optional;

import com.project.BookMyShow.Entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.BookMyShow.Entity.Theatre;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {
	
	
//	@Query("select t from Theatre t where t.city.cityId = :cityId")
//	Optional<List<Long>> findByCityId(@Param("cityId")Long cityId);


	Theatre findByTheatreName(String theatreName);

	List<Theatre> findByCity_CityId(Long cityId);

    List<Theatre> findByCity(City city);
}

