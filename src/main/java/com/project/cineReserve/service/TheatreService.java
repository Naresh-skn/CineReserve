package com.project.cineReserve.service;

import com.project.cineReserve.dto.CityDTO;
import com.project.cineReserve.dto.TheatreDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface TheatreService {
    CityDTO createNewCity(CityDTO cityDTO);

    List<CityDTO> getAllCities();

    CityDTO updateCity(CityDTO cityDTO);

    CityDTO deleteCity(Long cityId);

    TheatreDTO createTheatre(@Valid TheatreDTO theatreDTO);

    List<TheatreDTO> getTheatreFromCity(Long cityId);

    TheatreDTO updateTheatre(TheatreDTO theatreDTO);
}
