package com.parkingslot.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parkingslot.main.Entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
