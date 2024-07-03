package com.vedant.payloads;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter



public class UserDTO {
	
	
	private int id;		
	
	@NotBlank
	@Size(min = 4, message = "Username must be minimum of 4 characters")
	private String name;
	
	@Email(message = "Your given email adddress is not in correct format")
	private String email;
	
	private Set<RoleDTO> roles = new HashSet<>();
	
	@NotBlank
	@Size(min = 4,message = "Password must be between 4 to 10 characters")
	private String password;
	
	@NotBlank
	private String about;

}
