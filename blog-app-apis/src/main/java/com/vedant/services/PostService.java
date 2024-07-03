package com.vedant.services;

import java.util.List;

import com.vedant.entities.Post;
import com.vedant.payloads.PostDTO;
import com.vedant.payloads.PostResponse;

public interface PostService {
	
	PostDTO createPost(PostDTO postdto,Integer userId,Integer categoryId);
	
	PostDTO updatePost(PostDTO postdto, Integer postId);
	
	void deletePost(Integer postId);
	
	PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDir);
	
	PostDTO getPostbyId(Integer PostId);
	
	List<PostDTO> getPostByCategory(Integer categoryId);
	
	List<PostDTO> getPostByUser(Integer userId);
	
	List<PostDTO> searchPosts(String keyword);

}
