package com.vedant.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vedant.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	public Optional<User> findByEmail(String email);

}
