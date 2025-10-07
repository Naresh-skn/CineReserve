package com.project.cineReserve.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CityDTO {

    private Long cityId;

    private String cityName;

    private String state;

    private String country;
}
