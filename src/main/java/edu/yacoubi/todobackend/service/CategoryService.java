package edu.yacoubi.todobackend.service;

import edu.yacoubi.todobackend.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void save(Category category);
    List<Category> findAll();
    Optional<Category> findById(Long id);
    List<Category> findAllByUserId(Long userId);
    void delete(Long id);
}
