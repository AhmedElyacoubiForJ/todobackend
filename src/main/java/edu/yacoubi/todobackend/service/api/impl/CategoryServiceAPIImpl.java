package edu.yacoubi.todobackend.service.api.impl;

import edu.yacoubi.todobackend.dto.CategoryDTO;
import edu.yacoubi.todobackend.repository.CategoryRepository;
import edu.yacoubi.todobackend.service.api.CategoryServiceAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceAPIImpl implements CategoryServiceAPI {

    private final CategoryRepository repository;

    @Override
    public CategoryDTO creatNewCategory(CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return null;
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        return null;
    }

    @Override
    public List<CategoryDTO> findAllByUserId(Long userId) {
        return null;
    }

    @Override
    public void deleteCategoryById(Long id) {

    }
}
