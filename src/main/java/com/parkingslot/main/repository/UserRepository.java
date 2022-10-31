package com.parkingslot.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parkingslot.main.JwtSecurity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserName(String name);

}
