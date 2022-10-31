package com.parkingslot.main.Entity;

import java.util.List;

import com.parkingslot.main.DTO.BookingDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserEndResult {
	
	private String token;
	private List<BookingDto> bookDto;
		

}
