package com.vedant.services;

import com.vedant.payloads.CommentDTO;

public interface CommentService {
	
	CommentDTO createComment(CommentDTO commentdto, Integer PostId);
	
	void deleteComment(Integer commentId);

}
 