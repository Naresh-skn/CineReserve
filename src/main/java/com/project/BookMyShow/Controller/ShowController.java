package com.project.BookMyShow.Controller;


import com.project.BookMyShow.DTO.ResponseShowDTO;
import com.project.BookMyShow.DTO.ShowDTO;
import com.project.BookMyShow.Service.ShowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShowController {



    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

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

//    @GetMapping("/public/cities")
//    public ResponseEntity<List<CityDTO>> getAllCities(){
//
//
//    }
}
