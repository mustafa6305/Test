package com.parkingslot.main.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.parkingslot.main.Entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	@Query(value = "SELECT * FROM demo.booking where date=:date and slot_id=:sid and status=:val",nativeQuery = true)
	public Booking getBookingDetails(Date date,Long sid, String val);

	@Query(value = "SELECT * FROM demo.booking where user_id=:sid",nativeQuery = true)
	public List<Booking> findByUserId(@Param("sid")Long id);
}
