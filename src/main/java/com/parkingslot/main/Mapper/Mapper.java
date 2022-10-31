package com.parkingslot.main.Mapper;

import java.util.List;

import com.parkingslot.main.DTO.BookingDto;
import com.parkingslot.main.Entity.Booking;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {
	
   List<BookingDto> toBookingDTO (List<Booking> book);
}
