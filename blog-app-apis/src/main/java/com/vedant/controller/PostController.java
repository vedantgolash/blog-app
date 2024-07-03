package com.vedant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vedant.config.AppConstants;
import com.vedant.entities.Post;
import com.vedant.payloads.ApiResponse;
import com.vedant.payloads.PostDTO;
import com.vedant.payloads.PostResponse;
import com.vedant.services.FileService;
import com.vedant.services.PostService;

@RestController
@RequestMapping("/api/")

public class PostController {
	
	@Autowired
	private PostService postservice;
	
	@Autowired
	private FileService fileservice;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPosted(@RequestBody PostDTO postdto, @PathVariable Integer userId,@PathVariable Integer categoryId){
		
		PostDTO createdPost = this.postservice.createPost(postdto, userId, categoryId);
		
		return new ResponseEntity<PostDTO>(createdPost,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Integer userId){
		
		List<PostDTO> allposts = this.postservice.getPostByUser(userId);
		
		return new ResponseEntity<List<PostDTO>>(allposts,HttpStatus.OK);
		
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable Integer categoryId){
		List<PostDTO> allposts = this.postservice.getPostByCategory(categoryId);
		
		return new ResponseEntity<List<PostDTO>>(allposts, HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getallPosts(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber, 
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize, 
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir){
		PostResponse postresponse  = this.postservice.getAllPosts(pageNumber,pageSize,sortBy,sortDir);
		
		return new ResponseEntity<PostResponse>(postresponse,HttpStatus.OK);
	}
	
	@GetMapping("/post/{PostId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Integer PostId){
		PostDTO post = this.postservice.getPostbyId(PostId);
		
		return new ResponseEntity<PostDTO>(post,HttpStatus.OK); 
	}
	
	@DeleteMapping("/posts/{PostId}")
	public ResponseEntity<ApiResponse> deletepost(@PathVariable Integer PostId) {
		
		this.postservice.deletePost(PostId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("post deleted successfully",true),HttpStatus.OK);
		
		
	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postdto ,@PathVariable Integer postId){
		
		PostDTO updatedpost = this.postservice.updatePost(postdto, postId);
		
		return new ResponseEntity<PostDTO>(updatedpost,HttpStatus.OK);
	}
	
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDTO>> getPostByKeywords(@PathVariable String keywords){
		List<PostDTO> yes =  this.postservice.searchPosts(keywords);
		
		return new ResponseEntity<List<PostDTO>>(yes,HttpStatus.OK);
	}
	
//	@PostMapping("/post/image/upload/{postId}")
//	public ResponseEntity<ImageResponse> uploadPostImage(@RequestParam("image") MultipartFile image){
//		
//	}
	
	
	
	

}
