package com.parkingslot.main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.parkingslot.main.Entity.Availability;
import com.parkingslot.main.Exception.EndpointsConfig;
import com.parkingslot.main.Service.AvailabilityService;

@RestController
public class AvailabilityController {
	@Autowired
	private AvailabilityService availService;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(EndpointsConfig.SET_AVAILABILITY)
	public String availSlots(@PathVariable Long id, @RequestBody Availability avail)
	{
		return availService.slotsAvail(id,avail);
	}
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping(EndpointsConfig.UPDATE_AVAILABILITY)
	public String updateAvailSlot(@PathVariable Long sid, @PathVariable Long aid,@RequestBody Availability avail)
	{
		return availService.slotAvailUpdate(sid,aid,avail);
	}

}
