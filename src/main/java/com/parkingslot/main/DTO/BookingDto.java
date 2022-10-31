package com.parkingslot.main.DTO;

import java.util.Date;

import com.parkingslot.main.Entity.Slot;

import lombok.Data;
@Data
public class BookingDto 
{
  private Date date;
  private String status;
  private Slot slot;
}
