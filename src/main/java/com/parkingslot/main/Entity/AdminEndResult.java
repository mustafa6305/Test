package com.parkingslot.main.Entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminEndResult {
	private String token;
	private List<Building> buildings;
  
}
