package com.vedant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedant.payloads.ApiResponse;
import com.vedant.payloads.CommentDTO;
import com.vedant.services.CommentService;
import com.vedant.services.impl.CommentServiceImpl;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	
	@Autowired
	private CommentService commentservice;
	
	
	@PostMapping("/post/{PostId}/comments")
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentdto, @PathVariable("PostId") Integer PostId){
		
		CommentDTO savedcomment = this.commentservice.createComment(commentdto, PostId);
		
		return new ResponseEntity<CommentDTO>(savedcomment,HttpStatus.OK);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") Integer commentId){
		
		this.commentservice.deleteComment(commentId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("comment delete succesfully",true),HttpStatus.OK);
	}
	
	

}
