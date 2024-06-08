package com.abhi.electro.controller.admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.abhi.electro.dto.CategoryDto;
import com.abhi.electro.entity.Category;
import com.abhi.electro.services.admin.category.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AdminCategoryController {
	
	private final CategoryService categoryService;
	
	public AdminCategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	
	@PostMapping("/category")
	public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto){
		Category category = categoryService.createcategory(categoryDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(category);
	}
	
	@GetMapping("")
	public ResponseEntity<List<Category>> getAllCategories(){
		return ResponseEntity.ok(categoryService.getAllCategories());
	}
}

