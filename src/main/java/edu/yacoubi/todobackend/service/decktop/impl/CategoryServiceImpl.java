package edu.yacoubi.todobackend.service.decktop.impl;

import edu.yacoubi.todobackend.model.Category;
import edu.yacoubi.todobackend.repository.CategoryRepository;
import edu.yacoubi.todobackend.service.decktop.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    public Category createNewCategory(Category category) {
        return null;
    }

    @Override
    public List<Category> getCategories() {
        return null;
    }

    @Override
    public Category getCategoryById(Long id) {
        return null;
    }

    @Override
    public void deleteCategoryById(Long id) {

    }
}
