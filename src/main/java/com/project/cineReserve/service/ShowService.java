package com.project.cineReserve.service;

import com.project.cineReserve.dto.ResponseShowDTO;
import com.project.cineReserve.dto.ShowDTO;
import com.project.cineReserve.dto.ShowSeatDTO;

import java.util.List;

public interface ShowService {

    ShowDTO addNewShow(ShowDTO showDTO);

    List<ResponseShowDTO> getAllShowsFromCityAndMovie(Long cityId, Long movieId);

    List<ShowSeatDTO> getAllSeats(Long showId);
}

