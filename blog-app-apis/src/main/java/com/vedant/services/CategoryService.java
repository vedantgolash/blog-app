package com.vedant.services;

import java.util.List;

import com.vedant.payloads.CategoryDTO;

public interface CategoryService {
	
	public CategoryDTO createCategory(CategoryDTO categoryDTO);
	
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer CategoryId);
	
	public void deleteCategory(Integer CategoryId);
	
	public List<CategoryDTO> getAllCategory();
	
	public CategoryDTO getCategoryById(Integer CategoryId);
	
	
	
	

}
