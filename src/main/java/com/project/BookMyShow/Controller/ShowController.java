package com.project.BookMyShow.Controller;


import com.project.BookMyShow.DTO.CityDTO;
import com.project.BookMyShow.DTO.ShowDTO;
import com.project.BookMyShow.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping("/admin/show")
    public ResponseEntity<ShowDTO> createNewShow(
            @RequestBody ShowDTO showDTO
    ){
        ShowDTO savedShowDTO = showService.addNewShow(showDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedShowDTO);
    }

//    @GetMapping("/public/cities")
//    public ResponseEntity<List<CityDTO>> getAllCities(){
//
//
//    }
}
