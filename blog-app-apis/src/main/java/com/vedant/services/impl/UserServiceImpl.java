package com.vedant.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vedant.config.AppConstants;
import com.vedant.entities.Role;
import com.vedant.entities.User;
import com.vedant.exceptions.ResourceNotFoundException;
import com.vedant.payloads.UserDTO;
import com.vedant.repositories.RoleRepo;
import com.vedant.repositories.UserRepo;
import com.vedant.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userrepo;

	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RoleRepo rolerepo;
	
	
	
	
	@Override
	public UserDTO createUser(UserDTO userdto) {
		// TODO Auto-generated method stub
		
		User user = this.DTOtoUser(userdto);
		
		String pass = user.getPassword();
//		BCryptPasswordEncoder bcry;
		
		user.setPassword(encoder.encode(pass));
		
		
		
		
		User saveduser = this.userrepo.save(user);
		
		return this.UsertoDTO(saveduser);
		
		
	}

	@Override
	public UserDTO updateUser(UserDTO userdto, Integer userId) {
		
		User user = this.userrepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		user.setAbout(userdto.getAbout());
		user.setEmail(userdto.getEmail());
		user.setId(userdto.getId());
		user.setName(userdto.getName());
		user.setPassword(userdto.getPassword());
		
		User updateduser = this.userrepo.save(user);
		return this.UsertoDTO(updateduser);
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		
		User user = this.userrepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		return this.UsertoDTO(user);
	}

	@Override
	public List<UserDTO> getallusers() {
		
		List<User> users = this.userrepo.findAll();
		
		List<UserDTO> userdtos = users.stream().map(user-> this.UsertoDTO(user)).collect(Collectors.toList());
		
		return userdtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userrepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		this.userrepo.delete(user);

	}
	
	private User DTOtoUser(UserDTO userDto) {
		User user  = this.modelmapper.map(userDto, User.class);
		
		return user;
		
		
		
	}
	
	public UserDTO UsertoDTO(User user) {
		UserDTO userdto = this.modelmapper.map(user, UserDTO.class);
		
		
		return userdto;
	}

	@Override
	public UserDTO registerNewUser(UserDTO userdto) {
		// TODO Auto-generated method stub
		User user = this.modelmapper.map(userdto, User.class);
		
		user.setPassword(this.encoder.encode(user.getPassword()));
		
		Role role = this.rolerepo.findById(AppConstants.NORMAL_USER).get(); 
		
		user.getRoles().add(role);
		
		User newuser = this.userrepo.save(user);
		
//		user.getR
		
		
		return this.modelmapper.map(newuser,UserDTO.class);
	}

}
