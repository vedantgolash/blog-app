package com.vedant.services;

import java.util.List;

import com.vedant.payloads.UserDTO;

public interface UserService {
	
	UserDTO registerNewUser(UserDTO user);
	
	UserDTO createUser(UserDTO user);
	UserDTO updateUser(UserDTO userdto, Integer userId);
	UserDTO getUserById(Integer userId);
	List<UserDTO> getallusers();
	
	void deleteUser(Integer userId);
	
	
	

}
