package com.vedant.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.vedant.entities.Category;
import com.vedant.entities.Comment;
import com.vedant.entities.User;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor 

public class PostDTO {
	
	private Integer postId;
	
	private String title;
	

	private String content;
	
	private String imageName;
	private Date addedDate;
	
	
	private CategoryDTO category;
	
	
	private UserDTO user;
	
	private Set<CommentDTO> comments = new HashSet<>();
	

}
