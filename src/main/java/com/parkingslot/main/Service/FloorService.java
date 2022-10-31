package com.parkingslot.main.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingslot.main.Entity.Building;
import com.parkingslot.main.Entity.Floor;
import com.parkingslot.main.repository.BuildingRepository;
import com.parkingslot.main.repository.FloorRepository;

@Service
public class FloorService {
	
	@Autowired
	private FloorRepository floorrepo;
	@Autowired
	private BuildingRepository buildingrepo;
	

	public String floorAdd(Long id, Floor floor) {
		
		Optional<Building> optional= buildingrepo.findById(id);
		if(optional==null|| optional.isEmpty())
		{
			return "INVALID BUILDING ID";
		}
		Building build=buildingrepo.getReferenceById(id);
		System.out.println(build);
		Floor f= floorrepo.findByFloorNumber(id,floor.getFloorNumber());
		if(f ==null)
		{
			System.out.println("its null bro");
			List<Floor> floors = new ArrayList<>();
			floors.addAll(build.getFloor());
			floors.add(floor);
			build.setFloor(floors);
			buildingrepo.save(build);
			return " Floor added Successfully";	
		}
		else
			 return "You have already booked this floor try with another floor booking";	
	}


	public String floorDelete(Long bid, Long fid) {
		Optional<Building> opt= buildingrepo.findById(bid);
		if(opt==null||opt.isEmpty())
		{
			return "INVALID BUILDING ID";
		}
		Building build= buildingrepo.getReferenceById(bid);
		boolean flag= build.getFloor().stream().anyMatch(f->f.getId()==fid);
		if(!flag)
			return "NO Such Floor Exists in your Building";
		build.getFloor().removeIf(f->f.getId()==fid);
		floorrepo.deleteById(fid);
		buildingrepo.save(build);
		return "Floor Deleted Successfully";	
	}
	
	

}
