package com.vedant.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.vedant.entities.Comment;
import com.vedant.entities.Post;
import com.vedant.exceptions.ResourceNotFoundException;
import com.vedant.payloads.CommentDTO;
import com.vedant.repositories.CommentRepo;
import com.vedant.repositories.PostRepo;
import com.vedant.services.CommentService;
import com.vedant.services.PostService;

@Service

public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postrepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private CommentRepo commentrepo;

	@Override
	public CommentDTO createComment(CommentDTO commentdto, Integer PostId) {
		// TODO Auto-generated method stub
		
		Post post = this.postrepo.findById(PostId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post id", PostId));
		Comment comment = this.modelmapper.map(commentdto, Comment.class); 
		
		
		comment.setPost(post);
		
		Comment savedpost = this.commentrepo.save(comment);
		
		
		
		return this.modelmapper.map(savedpost, CommentDTO.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentrepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("CommentId", "Comment id", commentId));
		
		this.commentrepo.delete(comment);

	}

}
