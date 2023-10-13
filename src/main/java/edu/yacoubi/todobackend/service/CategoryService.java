package edu.yacoubi.todobackend.service;

import edu.yacoubi.todobackend.model.Category;

import java.util.List;

public interface CategoryService {
    void save(Category category);
    List<Category> findAll();
    Category findById(Long id);
    List<Category> findAllByUserId(Long userId);
    void delete(Long id);
}
