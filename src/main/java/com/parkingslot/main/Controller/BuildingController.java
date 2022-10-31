package com.parkingslot.main.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.parkingslot.main.Entity.Building;
import com.parkingslot.main.Exception.EndpointsConfig;
import com.parkingslot.main.Service.BuildingService;

@RestController
public class BuildingController {
	
	@Autowired
	private BuildingService buildingservice;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(EndpointsConfig.ADD_BUILDING)
	public ResponseEntity<String> addBuilding(@RequestBody Building build)
	{
		buildingservice.add(build);
		return ResponseEntity.ok("Building Added Successfully");
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(EndpointsConfig.FETCH_BOOKING)
	public ResponseEntity<List<Building>> fetchBuilding()
	{
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(buildingservice.BuildingFetch());
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping(EndpointsConfig.DELETE_BUILDING)
	public String deleteBuilding(@PathVariable Long id)
	{
		return buildingservice.buildingDelete(id);
	}

}
