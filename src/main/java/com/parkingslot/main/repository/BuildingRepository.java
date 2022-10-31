package com.parkingslot.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parkingslot.main.Entity.Building;

public interface BuildingRepository extends JpaRepository<Building, Long> {

	Building findByName(String name);

}
