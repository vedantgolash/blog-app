package com.vedant.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vedant.entities.Category;
import com.vedant.exceptions.ResourceNotFoundException;
import com.vedant.payloads.CategoryDTO;
import com.vedant.repositories.CategoryRepo;
import com.vedant.services.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryrepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		
		Category category = this.modelmapper.map(categoryDTO, Category.class);
		Category savedcategory = this.categoryrepo.save(category);
		
		return this.modelmapper.map(savedcategory, CategoryDTO.class);
		
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer CategoryId) {
		
		
		Category cat = this.categoryrepo.findById(CategoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Not Found with Id", CategoryId));
		
		cat.setCategoryTitle(categoryDTO.getCategoryTitle());
		cat.setCategoryDescription(categoryDTO.getCategoryDescription());
		
		Category savedcat = this.categoryrepo.save(cat);
		return this.modelmapper.map(savedcat, CategoryDTO.class);
		
	}

	@Override
	public void deleteCategory(Integer CategoryId) {
		Category cat = this.categoryrepo.findById(CategoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Not Found with Id", CategoryId));
		
		this.categoryrepo.delete(cat);

	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> catlist = this.categoryrepo.findAll();
		
		List<CategoryDTO> catdtos = catlist.stream().map(cat-> this.modelmapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
		
		
		return catdtos;
	}

	@Override
	public CategoryDTO getCategoryById(Integer CategoryId) {
		Category cat = this.categoryrepo.findById(CategoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Not found with Id", CategoryId));
		
		
		return this.modelmapper.map(cat, CategoryDTO.class);
	}

}
