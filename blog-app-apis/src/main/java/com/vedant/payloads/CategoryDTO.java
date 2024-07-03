package com.vedant.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class CategoryDTO {
	
	private Integer CategoryId;
	
	@NotBlank
	@Size(min = 4, message = "CategoryTitle must be minimum of 4 characters")
	private String CategoryTitle;
	
	@NotBlank
	@Size(min = 10, message = "CategoryDescription must be minimum of 10 characters")
	private String CategoryDescription;
	
	
	

}
