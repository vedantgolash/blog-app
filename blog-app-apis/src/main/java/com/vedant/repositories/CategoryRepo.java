package com.vedant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vedant.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
