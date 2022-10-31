package com.parkingslot.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingslot.main.Entity.Building;
import com.parkingslot.main.Exception.ResourceNotFoundException;
import com.parkingslot.main.repository.BuildingRepository;

@Service
public class BuildingService {
	
	@Autowired
	private BuildingRepository buildingrepo;

	public void add(Building build) {
		buildingrepo.save(build);	
	}

	public String buildingDelete(Long id) {
		 Building building = buildingrepo.findById(id).orElse(null);
		 if(building == null)
			 throw new ResourceNotFoundException("NO SUCH BUILDING WITH ID "+id+" EXISTS");
		 buildingrepo.deleteById(id);
		 
		 return "Deleted Building with id"+id+"Successfully";
	}

	public List<Building> BuildingFetch() {
		return buildingrepo.findAll();
	}

}
