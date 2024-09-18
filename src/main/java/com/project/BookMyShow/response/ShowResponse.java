package com.project.BookMyShow.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ShowResponse {
	
	 	private Long showId;
	    public ShowResponse(Long showId, Double price, LocalDateTime showTime) {
			this.showId = showId;
			this.price = price;
			this.showTime = showTime;
		}
		private Double price;
	    private LocalDateTime showTime;

	   

}
