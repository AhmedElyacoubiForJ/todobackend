package edu.yacoubi.todobackend.service.impl;

import edu.yacoubi.todobackend.model.Category;
import edu.yacoubi.todobackend.repository.CategoryRepository;
import edu.yacoubi.todobackend.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> findAllByUserId(Long userId) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
