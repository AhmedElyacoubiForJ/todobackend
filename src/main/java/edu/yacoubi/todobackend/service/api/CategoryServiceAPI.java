package edu.yacoubi.todobackend.service.api;

import edu.yacoubi.todobackend.dto.CategoryDTO;

import java.util.List;

public interface CategoryServiceAPI {
    CategoryDTO creatNewCategory(CategoryDTO categoryDTO);
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(Long id);

    List<CategoryDTO> getAllCategoriesByUserId(Long userId);
    void deleteCategoryById(Long id);
}
