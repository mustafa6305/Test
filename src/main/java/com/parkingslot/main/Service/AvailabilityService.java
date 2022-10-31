package com.parkingslot.main.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingslot.main.Entity.Availability;
import com.parkingslot.main.Entity.Slot;
import com.parkingslot.main.Exception.ResourceNotFoundException;
import com.parkingslot.main.repository.AvailabilityRepository;
import com.parkingslot.main.repository.SlotRepository;

@Service
public class AvailabilityService {
	@Autowired
	private AvailabilityRepository availRepo;
	@Autowired
	private SlotRepository slot;

	public String slotsAvail(Long id, Availability avail) {
		Optional<Slot> opt=slot.findById(id);
		if(opt==null||opt.isEmpty())
			throw new ResourceNotFoundException("NO SUCH SLOT WITH "+id+" AVAILABLE");
		Slot s=slot.getReferenceById(id);
		s.setAvail(avail);
		slot.save(s);
		return "AVAILABILITY SET FOR THE SLOT";
	}

	public String slotAvailUpdate(Long sid, Long aid, Availability avail) {
		Optional<Slot> opt=slot.findById(sid);
		
		if(opt==null||opt.isEmpty())
			return "NO SUCH SLOT AVAILABLE";
		Slot s=slot.getReferenceById(sid);
		Availability a=availRepo.getReferenceById(aid);
		if(avail.getFromDate()!=null)
			a.setFromDate(avail.getFromDate());
		if(avail.getToDate()!=null)
			a.setToDate(avail.getToDate());
		s.setAvail(a);
		slot.save(s);
	    return "Slot Updated Successfully";
	}

}
