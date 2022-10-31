package com.parkingslot.main.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Slot")
@Data
@Getter
@Setter
public class Slot {
	@Id
	@GeneratedValue
	private Long id;
	private Integer slotNumber;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Availibility_id",referencedColumnName = "id")
	private Availability avail;
	
}
