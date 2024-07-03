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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vedant.entities.Category;
import com.vedant.payloads.ApiResponse;
import com.vedant.payloads.CategoryDTO;
import com.vedant.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryservice;
	
	
	@PostMapping("/")
	
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDto){
		
		CategoryDTO createdcat = this.categoryservice.createCategory(categoryDto);
		
		return new ResponseEntity<CategoryDTO>(createdcat,HttpStatus.OK);
	}
	
	@PutMapping("/{catid}")
	
	public ResponseEntity<CategoryDTO> updatedCategory(@Valid @RequestBody CategoryDTO categoryDto, @PathVariable("catid") Integer catid){
		
		CategoryDTO updatedCat = this.categoryservice.updateCategory(categoryDto, catid);
		
		return new ResponseEntity<CategoryDTO>(updatedCat, HttpStatus.OK);
		
		
		
		
	}
	
	@DeleteMapping("/{catid}")
	
	public ResponseEntity<ApiResponse> deletedCategory(@PathVariable("catid") Integer catid){
		
		this.categoryservice.deleteCategory(catid);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted successfully",true),HttpStatus.OK);
		
	}
	
	@GetMapping("/{catid}")
	
	public ResponseEntity<CategoryDTO> categorybyID(@PathVariable("catid") Integer catid){
		
		CategoryDTO catdto = this.categoryservice.getCategoryById(catid);
		
		return new ResponseEntity<CategoryDTO>(catdto,HttpStatus.OK);
	}
	
	@GetMapping("/")
	
	public ResponseEntity<List<CategoryDTO>> catdtos(){
		
		List<CategoryDTO> catdtoss = this.categoryservice.getAllCategory();
		
		return ResponseEntity.ok(catdtoss);
		
	}

}
