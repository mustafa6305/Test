package com.parkingslot.main.JwtSecurity;

import java.util.List;

import javax.security.sasl.AuthenticationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.parkingslot.main.DTO.BookingDto;
import com.parkingslot.main.Entity.AdminEndResult;
import com.parkingslot.main.Entity.Booking;
import com.parkingslot.main.Entity.UserEndResult;
import com.parkingslot.main.Mapper.Mapper;
import com.parkingslot.main.repository.BookingRepository;
import com.parkingslot.main.repository.BuildingRepository;


@RestController
public class UserController {

	@Autowired
	private JWTToken jwtToken;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserServiceImpl service;

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private BuildingRepository buildRepo;
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private Mapper mapper;



	@GetMapping("/getUser")
	public List<User> getAll(@RequestBody User user) {
		return service.getUsers();
	}

	@GetMapping("/getUserById")
	public User getById() {
		return service.getUserById();
	}

	@PostMapping("/addUser")
	public String saveUser(@Valid @RequestBody User user) {
		user.setRole(user.getRole().toUpperCase());
		return service.saveUser(user);
		
	}
	@PostMapping("/UserLogin")
	public Object loginUser(@RequestBody UserRequest userRequest){
        
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
		
		String token = jwtUtil.generateToken(userRequest.getUsername());
		
		String userName=jwtUtil.getUsername(token);
		User user= service.findByUsername(userName);
         if(user.getRole().equals("ADMIN"))
         {
        	 return new AdminEndResult(token, buildRepo.findAll());
         }
         
         List<Booking> bookings= bookingRepository.findByUserId(user.getId());
        //Stream<Object> id= bookings.stream().map(b->b.getSlot().getId());
         List<BookingDto> bookingDetails= mapper.toBookingDTO(bookings);
		return new UserEndResult(token,bookingDetails);

	}
	@PutMapping("/editUser")
	public String editUser(User user) {
		return service.userEdit(user);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteEmp(@PathVariable Long id) {
		return service.deleteById(id);
		
		

	}

}