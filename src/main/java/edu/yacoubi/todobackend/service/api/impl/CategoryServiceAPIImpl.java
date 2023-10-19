package edu.yacoubi.todobackend.service.api.impl;

import edu.yacoubi.todobackend.dto.CategoryDTO;
import edu.yacoubi.todobackend.model.Category;
import edu.yacoubi.todobackend.service.api.CategoryServiceAPI;
import edu.yacoubi.todobackend.service.delegate.CategoryServiceDelegate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceAPIImpl implements CategoryServiceAPI {

    private final CategoryServiceDelegate categoryServiceDelegate;

    @Override
    public CategoryDTO creatNewCategory(CategoryDTO categoryDTO) {
        log.info("CategoryServiceAPIImpl:creatNewCategory execution started.");

        log.debug("CategoryServiceAPIImpl:creatNewCategory request parameter {}", categoryDTO);

        Category toCategory = CategoryDTO.toEntity(categoryDTO);

        log.debug("CategoryServiceAPIImpl:CategoryServiceDelegate call start");
        Category categoryResult = categoryServiceDelegate
                .getCategoryService()
                .createNewCategory(toCategory);
        log.debug("CategoryServiceAPIImpl:CategoryServiceDelegate call return");

        CategoryDTO toCategoryDTOResult = CategoryDTO.fromEntity(categoryResult);

        log.info("CategoryServiceAPIImpl:creatNewCategory execution end.");
        return toCategoryDTOResult;
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
    public List<CategoryDTO> getAllCategoriesByUserId(Long userId) {
        return null;
    }

    @Override
    public void deleteCategoryById(Long id) {}
}
