package com.parkingslot.main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.parkingslot.main.Entity.Slot;
import com.parkingslot.main.Exception.EndpointsConfig;
import com.parkingslot.main.Service.SlotService;

@RestController
public class SlotContoller {
	
	@Autowired
	private SlotService slotService;
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping(EndpointsConfig.ADD_SLOTS)
	public String addSlot(@PathVariable String name, @PathVariable Long id, @RequestBody Slot slot) throws Exception
	{
		return slotService.slotAdd(name,id,slot);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping(EndpointsConfig.DELETE_SLOT)
	public String deleteSlot( @PathVariable Long sid)
	{
		return slotService.slotDelete(sid);
	}

}
