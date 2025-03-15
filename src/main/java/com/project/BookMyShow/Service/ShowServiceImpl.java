package com.project.BookMyShow.Service;

import com.project.BookMyShow.DTO.ShowDTO;
import com.project.BookMyShow.Entity.Movie;
import com.project.BookMyShow.Entity.Seat;
import com.project.BookMyShow.Entity.Show;
import com.project.BookMyShow.Entity.Theatre;
import com.project.BookMyShow.Repository.MovieRepository;
import com.project.BookMyShow.Repository.SeatRepository;
import com.project.BookMyShow.Repository.ShowRepository;
import com.project.BookMyShow.Repository.TheatreRepository;
import com.project.BookMyShow.exception.GenException;
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
    private final SeatRepository seatRepository;

    @Override
    @Transactional
    public ShowDTO addNewShow(ShowDTO showDTO) {
        Movie movie = movieRepository.findById(showDTO.getMovieId())
                .orElseThrow(()->new GenException("No Movie Found"));
        Theatre theatre = theatreRepository.findById(showDTO.getTheatreId())
                .orElseThrow(()->new GenException("No Theatre Found"));
        Show show = modelMapper.map(showDTO,Show.class);

        List<Seat> seats = new ArrayList<>();
        for(int i=0;i<theatre.getCapacity();i++){
            Seat seat = new Seat();
            seat.setShow(show);
            seat.setIsBooked(false);
            seat.setSeatNumber("s"+i);
            seats.add(seat);
        }
        show.setMovie(movie);
        show.setSeats(seats);
        show.setTheatre(theatre);
        Show savedShow = showRepository.save(show);
        seatRepository.saveAll(seats);
        return modelMapper.map(savedShow,ShowDTO.class);
    }
}
