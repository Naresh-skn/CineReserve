package com.project.BookMyShow.Service;

import com.project.BookMyShow.DTO.CityDTO;
import com.project.BookMyShow.DTO.TheatreDTO;
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
