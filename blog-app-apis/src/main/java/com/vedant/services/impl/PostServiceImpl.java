package com.vedant.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vedant.entities.Category;
import com.vedant.entities.Post;
import com.vedant.entities.User;
import com.vedant.exceptions.ResourceNotFoundException;
import com.vedant.payloads.PostDTO;
import com.vedant.payloads.PostResponse;
import com.vedant.payloads.UserDTO;
import com.vedant.repositories.CategoryRepo;
import com.vedant.repositories.PostRepo;
import com.vedant.repositories.UserRepo;
import com.vedant.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	
	@Autowired
	private PostRepo postrepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private CategoryRepo categoryrepo;

	@Override
	public PostDTO createPost(PostDTO postdto,Integer userId,Integer categoryId) {
		
		User user = this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User id", userId));
		
		Category category = this.categoryrepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category id", categoryId));
		
		Post post = this.modelmapper.map(postdto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post savedPost = this.postrepo.save(post);
		
		return this.modelmapper.map(savedPost, PostDTO.class);
		 
		
	}

	@Override
	public PostDTO updatePost(PostDTO postdto, Integer postId) {
		
		Post post = this.postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post id", postId));
		
		post.setTitle(postdto.getTitle());
		post.setContent(postdto.getContent());
		post.setImageName(postdto.getImageName());
		
		Post savedpost = this.postrepo.save(post);
		
		PostDTO convertedpost = this.modelmapper.map(savedpost, PostDTO.class);
		
		return convertedpost;
		
	}

	@Override
	public void deletePost(Integer postId) {
			Post post = this.postrepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post id", postId)); 
		
			this.postrepo.delete(post);;

	}

	@Override
	public PostResponse getAllPosts(int pagenumber, int pagesize, String sortBy, String sortDir) {
		
		
		Sort sort = null;
		
		if(sortDir.contains("asc")) {
			sort = Sort.by(sortBy).ascending();
		}
		else {
			sort = Sort.by(sortBy).descending();
		}
				
		
		Pageable p = PageRequest.of(pagenumber, pagesize,sort);
		
		Page<Post> pagePost = this.postrepo.findAll(p);
		
		List<Post> allposts = pagePost.getContent();
		
		List<PostDTO> convertedposts = allposts.stream().map(post->this.modelmapper.map(post, PostDTO.class)).collect(Collectors.toList());
		// TODO Auto-generated method stub
		
		PostResponse postrespo = new PostResponse();
		postrespo.setContent(convertedposts);
		postrespo.setPageNumber(pagePost.getNumber());
		postrespo.setPageSize(pagePost.getSize());
		postrespo.setTotalElements(pagePost.getTotalElements());
		postrespo.setTotalPages(pagePost.getTotalPages());
		postrespo.setLastPage(pagePost.isLast());
		
		return postrespo; 
	}

	@Override
	public PostDTO getPostbyId(Integer PostId) {
		Post post = this.postrepo.findById(PostId).orElseThrow(()->new ResourceNotFoundException("Post", "Post id", PostId));
		PostDTO convertedPost = this.modelmapper.map(post,PostDTO.class);
		
		
		return convertedPost;
	}

	@Override
	public List<PostDTO> getPostByCategory(Integer categoryId) {
		
		Category cat = this.categoryrepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "Category id", categoryId));
		// TODO Auto-generated method stub
		List<Post> posts = this.postrepo.findByCategory(cat);
		
		List<PostDTO> convertedPosts = posts.stream().map(post-> this.modelmapper.map(post, PostDTO.class)).collect(Collectors.toList());
		
		return convertedPosts;
	}

	@Override
	public List<PostDTO> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		
		User user = this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "user id", userId));
		
		List<Post> posts = this.postrepo.findByUser(user);
		
		List<PostDTO> convertedPosts = posts.stream().map(userd-> this.modelmapper.map(userd, PostDTO.class)).collect(Collectors.toList());
		
		return convertedPosts;
	}

	@Override
	public List<PostDTO> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		List<Post> posts = this.postrepo.findByTitle("%"+keyword+"%");
		
		List<PostDTO> convertedposts = posts.stream().map(post->this.modelmapper.map(post, PostDTO.class)).collect(Collectors.toList());
		
		return convertedposts;
	}

}
