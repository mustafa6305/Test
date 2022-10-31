package com.parkingslot.main.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parkingslot.main.Exception.EndpointsConfig;
import com.parkingslot.main.Service.BookingService;

@RestController
public class BookingController {
	@Autowired
	private BookingService bookService;
	
	@PostMapping(EndpointsConfig.BOOK_SLOTS)
	public String slotBook(@PathVariable Long bid,@PathVariable Long fid, @PathVariable Long sid,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		return bookService.addBooking(bid,fid, sid, date);

	}
	
	@DeleteMapping(EndpointsConfig.CANCEL_BOOKING)
	public String cancelBooking(@PathVariable Long id)
	{
		return bookService.bookingCancel(id);
	}


}
