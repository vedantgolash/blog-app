package com.vedant.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedant.payloads.ApiResponse;
import com.vedant.payloads.UserDTO;
import com.vedant.services.UserService;

import jakarta.validation.Valid;
import lombok.Getter;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	
	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userdto)
	{
		UserDTO createdUserDTO = this.userservice.createUser(userdto); 
		
		return new ResponseEntity<>(createdUserDTO,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{UserId}")
	
	public ResponseEntity<UserDTO> updatedUser(@Valid @RequestBody UserDTO userdto , @PathVariable("UserId") Integer uid){
		
		UserDTO updatedUser = this.userservice.updateUser(userdto, uid);
		
		return ResponseEntity.ok(updatedUser);
		
		
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{UserId}")
	
	public ResponseEntity<ApiResponse> deleteUser(@Valid @PathVariable("UserId") Integer uid){
		
		this.userservice.deleteUser(uid);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully",true), HttpStatus.OK);
	}
	
	@GetMapping("/")
	
	public ResponseEntity<List<UserDTO>> getUsers(){
		
		return ResponseEntity.ok(this.userservice.getallusers());
	}
	
	@GetMapping("/{UserId}")
	
	public ResponseEntity<UserDTO> getSingleUser(@Valid @PathVariable("UserId") Integer uid){
		
		return ResponseEntity.ok(this.userservice.getUserById(uid));
	}
	
	

}
