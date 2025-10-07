package com.project.cineReserve.controller;


import com.project.cineReserve.dto.CityDTO;
import com.project.cineReserve.dto.TheatreDTO;
import com.project.cineReserve.service.TheatreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TheatreController {

    private final TheatreService theatreService;

    @PostMapping("/admin/city")
    public ResponseEntity<CityDTO> createNewCity(
            @RequestBody CityDTO cityDTO
    ){
        CityDTO savedCityDTO = theatreService.createNewCity(cityDTO);
        return new ResponseEntity<>(savedCityDTO, HttpStatus.CREATED);
    }

    @GetMapping("/public/cities")
    public ResponseEntity<List<CityDTO>> getAllCities(){
        List<CityDTO> cityDTOList = theatreService.getAllCities();
        return new ResponseEntity<>(cityDTOList, HttpStatus.CREATED);
    }

    @PutMapping("/admin/city")
    public ResponseEntity<CityDTO> updateCity(
            @RequestBody CityDTO cityDTO
    ){
        CityDTO updatedCityDTO = theatreService.updateCity(cityDTO);
        return new ResponseEntity<>(updatedCityDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/city")
    public ResponseEntity<CityDTO> deleteCity(
            @RequestParam Long cityId
    ){
        CityDTO deleteCityDTO = theatreService.deleteCity(cityId);
        return new ResponseEntity<>(deleteCityDTO, HttpStatus.CREATED);
    }

    @PostMapping("/admin/theatre")
    public ResponseEntity<TheatreDTO> createNewTheatre(@Valid @RequestBody TheatreDTO theatreDTO) {
        TheatreDTO savedTheatreDTO = theatreService.createTheatre(theatreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTheatreDTO);
    }

    @GetMapping("/public/theatres")
    public ResponseEntity<List<TheatreDTO>> getTheatreFromCity(@RequestParam Long cityId){
        List<TheatreDTO> theatreDTOList = theatreService.getTheatreFromCity(cityId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(theatreDTOList);
    }

    @PutMapping("/admin/theatre")
    public ResponseEntity<TheatreDTO> updateTheatre(
            @RequestBody TheatreDTO theatreDTO
    ){
        TheatreDTO updatedTheatreDTO = theatreService.updateTheatre(theatreDTO);
        return new ResponseEntity<>(updatedTheatreDTO, HttpStatus.CREATED);
    }
}
