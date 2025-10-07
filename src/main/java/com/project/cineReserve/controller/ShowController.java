package com.project.cineReserve.controller;


import com.project.cineReserve.dto.ResponseShowDTO;
import com.project.cineReserve.dto.ShowDTO;
import com.project.cineReserve.dto.ShowSeatDTO;
import com.project.cineReserve.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    @PostMapping("/admin/show")
    public ResponseEntity<ShowDTO> createNewShow(
            @RequestBody ShowDTO showDTO
    ){
        ShowDTO savedShowDTO = showService.addNewShow(showDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedShowDTO);
    }

    @GetMapping("/shows/movie/{movieId}/city/{cityId}")
	public ResponseEntity<List<ResponseShowDTO>> getAllShowsFromCityAndMovie(
			@PathVariable("cityId") Long cityId,
			@PathVariable("movieId") Long movieId){
		List<ResponseShowDTO> shows = showService.getAllShowsFromCityAndMovie(cityId,movieId);
		return ResponseEntity.status(HttpStatus.OK).body(shows);//<>(shows,HttpStatus.OK);
	}


	@GetMapping("seats/{showId}")
	public ResponseEntity<List<ShowSeatDTO>> getSeatsFromShow(@PathVariable("showId") Long showId){
		List<ShowSeatDTO> seats = showService.getAllSeats(showId);
		return ResponseEntity.status(HttpStatus.OK).body(seats);
	}
}
