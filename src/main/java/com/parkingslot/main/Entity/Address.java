package com.parkingslot.main.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Address {
	@Id
	@GeneratedValue
	private Long id;
	private String landmark;
	private String city;
	private String state;
	private String pincode;
}
