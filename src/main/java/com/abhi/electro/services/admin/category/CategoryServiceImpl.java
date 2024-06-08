package com.abhi.electro.services.admin.category;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abhi.electro.dto.CategoryDto;
import com.abhi.electro.entity.Category;
import com.abhi.electro.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}
	
	public Category createcategory(CategoryDto categoryDto) {
		Category category = new Category();
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());
		
		return categoryRepository.save(category);
	}
	
	public List<Category> getAllCategories(){
		return categoryRepository.findAll();
	}

}
