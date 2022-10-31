package com.parkingslot.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parkingslot.main.Entity.Slot;

public interface SlotRepository extends JpaRepository<Slot, Long> {
    //@Query(value="select s,f from Slot s, Floor f where s.slotNumber in f",nativeQuery = true)
	//Slot findBySlotNumber();

}
