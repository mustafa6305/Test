package com.parkingslot.main.JwtSecurity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private String userName;
	@NotNull
	@Email(message = "Invalid Email")
	@Column(unique = true)
	private String email;
	
	//@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Password length should be atleast 8 and should be Alphanumeric!!!")
	private String password;
	
	
	private String role;
	
	

}
