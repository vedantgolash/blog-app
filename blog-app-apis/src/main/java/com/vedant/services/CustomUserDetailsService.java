package com.vedant.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vedant.entities.User;
import com.vedant.exceptions.ResourceNotFoundException;
import com.vedant.repositories.UserRepo;


@Service 
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepo userrepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = userrepo.findByEmail(username).orElseThrow(()-> new RuntimeException("Username Not Found"));
		return (UserDetails) user;
	}

}
