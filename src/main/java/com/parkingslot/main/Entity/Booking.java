package com.parkingslot.main.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.parkingslot.main.JwtSecurity.User;

import lombok.Data;

@Entity
@Data
public class Booking {
	@Id
	@GeneratedValue
	private Long id;
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
//	private Date fromDate;
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
//	private Date toDate;

	@Temporal(TemporalType.DATE)
	private Date date;
	@JsonIgnore
	private String status;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "slotId")
	private Slot slot;

}
