package com.parkingslot.main.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingslot.main.Entity.Building;
import com.parkingslot.main.Entity.Floor;
import com.parkingslot.main.Entity.Slot;
import com.parkingslot.main.repository.BuildingRepository;
import com.parkingslot.main.repository.FloorRepository;
import com.parkingslot.main.repository.SlotRepository;

@Service
public class SlotService {

	@Autowired
	private SlotRepository slotRepo;
	@Autowired
	private FloorRepository floorRepo;
	@Autowired
	private BuildingRepository buildRepo;

	public String slotAdd(String name, Long id, Slot slot) throws Exception {
		Building opt = buildRepo.findByName(name);
//		System.out.println(opt.getName());
//		System.out.println(name);
		if (!(opt.getName().equals(name))) {
			return "No Such Building is available";
		}
		Optional<Floor> fopt = floorRepo.findById(id);
		if (fopt == null || fopt.isEmpty()) {
			return "INVALID FLOOR ID";
		}
		Floor f = floorRepo.getReferenceById(id);
		List<Slot> list1 = new ArrayList<>();
		list1.addAll(f.getSlot());
		for (Slot i : list1) {
			if (i.getSlotNumber() == slot.getSlotNumber())
				return "Slot already exits";
		}
		if (f != null) {
			List<Slot> slotlist = new ArrayList<>();
			slotlist.addAll(f.getSlot());
			slotlist.add(slot);
			f.setSlot(slotlist);
			buildRepo.save(opt);
			return "Slot added Successfully";
		}
		else
			return "Slot already exists";
	}

	public String slotDelete(Long sid){
//		Building build=buildRepo.getReferenceById(bid);
//		if(build==null)
//			return "INVALID BUILDING ID";
//		boolean flag=build.getFloor().stream().anyMatch(f->f.getId()==fid);
//		Floor f=floorRepo.getReferenceById(fid);
//		//Slot slot=slotRepo.getReferenceById(sid);
//		List<Slot> slist=new ArrayList<>();
//		if(flag)
//		{
//			slist.addAll(f.getSlot());
//			boolean b= slist.stream().anyMatch(s->s.getId()==sid);
//			if(!b)
//				return "No Such Slots exists in your floor";
//			slotRepo.deleteById(sid);
//			buildRepo.save(build);
//			return "Slot Deleted Successfully";
//		}
//		else
//			return "THIS FLOOR WITH "+ fid +" DOESNOT EXIST IN YOUR BUILDING ID"+bid;		
//	}
		slotRepo.deleteById(sid);
		return "Deleted Successfully";
	}

}
