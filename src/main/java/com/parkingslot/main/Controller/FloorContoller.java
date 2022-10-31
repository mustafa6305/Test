package com.parkingslot.main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.parkingslot.main.Entity.Floor;
import com.parkingslot.main.Exception.EndpointsConfig;
import com.parkingslot.main.Service.FloorService;

@RestController
public class FloorContoller {
	
	@Autowired
	private FloorService floorservice;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping(EndpointsConfig.ADD_FLOORS)
	public String addFloor(@PathVariable Long id, @RequestBody Floor floor)
	{
		return floorservice.floorAdd(id,floor);
		
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping(EndpointsConfig.DELETE_FLOOR)
	public String deleteFloor(@PathVariable Long bid, @PathVariable Long fid )
	{
		return floorservice.floorDelete(bid,fid);
	}

}
