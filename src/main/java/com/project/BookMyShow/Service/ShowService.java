package com.project.BookMyShow.Service;

import com.project.BookMyShow.DTO.ResponseShowDTO;
import com.project.BookMyShow.DTO.ShowDTO;
import com.project.BookMyShow.DTO.ShowSeatDTO;

import java.util.List;

public interface ShowService {

    ShowDTO addNewShow(ShowDTO showDTO);

    List<ResponseShowDTO> getAllShowsFromCityAndMovie(Long cityId, Long movieId);

    List<ShowSeatDTO> getAllSeats(Long showId);
}

