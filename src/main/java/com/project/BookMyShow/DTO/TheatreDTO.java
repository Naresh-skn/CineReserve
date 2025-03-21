package com.project.BookMyShow.DTO;

import com.project.BookMyShow.Entity.SeatTypeConfiguration;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TheatreDTO {

	private Long theatreId;

	@NotBlank(message = "Theatre name cannot be blank")
	private String theatreName;

	@NotNull(message = "City ID is required")
	private Long cityId;

	private Integer capacity;

	private String address;

	@Valid
	@Size(min = 1, message = "At least one seat configuration is required")
	private List<SeatTypeConfigurationDTO> seatConfigurations;
}
