package com.parkingslot.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parkingslot.main.Entity.Availability;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

}
