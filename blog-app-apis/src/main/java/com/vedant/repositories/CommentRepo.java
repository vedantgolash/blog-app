package com.vedant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vedant.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
