package com.parkingslot.main.JwtSecurity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parkingslot.main.repository.UserRepository;

@Service
public class UserServiceImpl extends HttpServlet implements  UserDetailsService {

	private static final long serialVersionUID = 1L;
	@Autowired
	private com.parkingslot.main.repository.UserRepository repository;
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	@Autowired
	private UserRepository userRepo;

	public Long getId() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		User user = repository.findByUserName(name).orElse(null);
		return user.getId();
	}

	public User findByUsername(String username) {
		Optional<User> user = repository.findByUserName(username);
		if (user.isPresent())
			return user.get();
		return null;
	}

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) {
		User user = findByUsername(username);
		System.out.println(user);
		System.out.println(user==null);
		
		if (user==null) {
			throw new BadCredentialsException("Invalid Username or passsword");
		}
			
		ArrayList<String> roles = new ArrayList<>();
		roles.add(user.getRole());
		List<GrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
	}


	public String getUserRoles() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$"+name);
		User user = repository.findByUserName(name).orElse(null);
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+user.getRole());
		return user.getRole();
	}

	public List<User> getUsers() {
		return userRepo.findAll();
		
	}


	public User getUserById() {
		User user=userRepo.getReferenceById(getId());
		if(user==null)
			return null;
		return user;
		
	}
	public String saveUser(User user) {
		user.setPassword(pwdEncoder.encode(user.getPassword()));
		repository.save(user);
		return "USER ADDED SUCCESSFULLY";
	}


	public String userEdit(User user) {
		User u= userRepo.getReferenceById(getId());
		if(u==null)
			return "NO SUCH USER WITH"+getId()+" EXISTS";
		if(user.getUserName()!=null)
			u.setUserName(user.getUserName());
		if(user.getPassword()!=null)
		{
			user.setPassword(pwdEncoder.encode(user.getPassword()));
			u.setPassword(user.getPassword());
		}
		if(user.getEmail()!=null)
			u.setEmail(user.getEmail());
		if(user.getRole()!=null)
			u.setRole(user.getRole());
		userRepo.save(u);
		return "USER UPDATED SUCCESSFULLY";
	}
	public String deleteById(Long id) {
		User user = repository.getReferenceById(getId());
		if(user==null)
			return "NO SUCH ID EXISTS";
		if (user.getRole().equals("ADMIN")) {
		userRepo.deleteById(id);
	}
		return "USER DELETED SUCCESSFULLY";
}
}