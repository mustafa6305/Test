package com.parkingslot.main.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Entity
@Data
public class Building {
	@Id
	@GeneratedValue
	private Long id;
	
	@Pattern(regexp = "^[A-Za-z]\\w{7,19}$")
	private String name;
	
	@Column(unique = true)
	private Integer number;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Add_id",referencedColumnName = "id")
	private com.parkingslot.main.Entity.Address address;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "Build_id",referencedColumnName = "id")
	private List<Floor> floor;
	
	

}
