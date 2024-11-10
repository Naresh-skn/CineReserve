package com.project.BookMyShow.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TheatreDTO {
	
	private String name;
	
	private Long city;
	
	private Integer capacity;
	
	private String Address;

}
