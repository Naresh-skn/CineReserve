package com.project.BookMyShow.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TheatreDTO {

	private Long theatreId;

	@NotBlank
	private String theatreName;

	@NotNull
	private Long cityId;
	
	private Integer capacity;
	
	private String address;

}
