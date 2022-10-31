package com.parkingslot.main.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity(name = "Floor")
@Data
public class Floor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Integer floorNumber;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "floor_id",referencedColumnName = "id")
	private List<Slot> slot;

}
