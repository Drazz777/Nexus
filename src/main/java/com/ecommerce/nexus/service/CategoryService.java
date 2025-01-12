package com.ecommerce.nexus.service;

import com.ecommerce.nexus.payload.CategoryDTO;
import com.ecommerce.nexus.payload.CategoryResponse;

public interface CategoryService {

    CategoryResponse getAllCategories(int pageNum, int pageLimit, String sortBy, String sortOrder);

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);

}
