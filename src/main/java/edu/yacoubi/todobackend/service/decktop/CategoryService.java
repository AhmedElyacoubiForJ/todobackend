package edu.yacoubi.todobackend.service.decktop;

import edu.yacoubi.todobackend.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category createNewCategory(Category category);
    List<Category> getCategories();
    Category getCategoryById(Long id);
    void deleteCategoryById(Long id);
}
