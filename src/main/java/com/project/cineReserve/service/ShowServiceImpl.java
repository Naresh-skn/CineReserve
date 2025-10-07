package com.project.cineReserve.service;

import com.project.cineReserve.dto.ResponseShowDTO;
import com.project.cineReserve.dto.ShowDTO;
import com.project.cineReserve.dto.ShowSeatDTO;
import com.project.cineReserve.entity.*;
import com.project.cineReserve.repository.MovieRepository;
import com.project.cineReserve.repository.ShowRepository;
import com.project.cineReserve.repository.ShowSeatRepository;
import com.project.cineReserve.repository.TheatreRepository;
import com.project.cineReserve.exception.GenException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService{


    private final ShowRepository showRepository;
    private final TheatreRepository theatreRepository;
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;
    private final ShowSeatRepository showSeatRepository;

    @Override
    @Transactional
    public ShowDTO addNewShow(ShowDTO showDTO) {

        Movie movie = movieRepository.findById(showDTO.getMovieId())
                .orElseThrow(()->new GenException("No Movie Found"));
        Theatre theatre = theatreRepository.findById(showDTO.getTheatreId())
                .orElseThrow(()->new GenException("No Theatre Found"));
        Show show = modelMapper.map(showDTO,Show.class);

        List<ShowSeat> showSeats = new ArrayList<>();
        for(SeatTypeConfiguration seatTypeConfiguration: theatre.getSeatConfigurations()){
            for (int i=1;i<=seatTypeConfiguration.getSeatCount();i++) {
                ShowSeat showSeat = new ShowSeat();
                showSeat.setShow(show);
                showSeat.setSeatNumber("seat:" + i);
                showSeat.setSeatType(seatTypeConfiguration.getSeatType());
                showSeat.setPrice(seatTypeConfiguration.getPrice());
                showSeats.add(showSeat);
            }
        }
        show.setMovie(movie);
        show.setSeats(showSeats);
        show.setTheatre(theatre);
        Show savedShow = showRepository.save(show);
//        seatRepository.saveAll(showSeats);
        return modelMapper.map(savedShow,ShowDTO.class);
    }


    @Override
    public List<ResponseShowDTO> getAllShowsFromCityAndMovie(Long cityId, Long movieId) {

        List<Show> shows = showRepository.findShowsByMovieAndCity(movieId,cityId);
        if(shows.isEmpty())
            throw new GenException("No Shows found for this Movie");

        return shows.stream().map(
                show -> modelMapper.map(show,ResponseShowDTO.class)
        ).toList();
    }

    @Override
    public List<ShowSeatDTO> getAllSeats(Long showId) {
        List<ShowSeat> seats = showSeatRepository.findByShow_ShowId(showId);
		if(seats.isEmpty())
		    throw new GenException("No Seats found");
        return seats.stream().map(showSeat ->
                modelMapper.map(showSeat,ShowSeatDTO.class)).toList();
    }

}
