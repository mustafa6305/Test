package com.parkingslot.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.parkingslot.main.Entity.Floor;

public interface FloorRepository extends JpaRepository<Floor, Long> {

	@Query( value = "select * from demo.floor where build_id=:id and floor_number=:n",nativeQuery = true)
	Floor findByFloorNumber(Long id, Integer n);

}
