package com.abhi.electro.services.admin.category;

import java.util.List;

import com.abhi.electro.dto.CategoryDto;
import com.abhi.electro.entity.Category;

public interface CategoryService {
	
	Category createcategory(CategoryDto categoryDto);
	
	List<Category> getAllCategories();

}
