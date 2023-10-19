package edu.yacoubi.todobackend.service.decktop.impl;

import edu.yacoubi.todobackend.exception.InvalidArgumentException;
import edu.yacoubi.todobackend.exception.ServiceBusinessException;
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

        log.info("CategoryServiceImpl:createNewCategory execution started.");

        if (category == null) {
            log.error("Exception occurred while validating parameter, category is null");
            throw new InvalidArgumentException("category must not be null");
        }

        Category categoryResult;
        try {
            log.debug("CategoryServiceImpl:createNewCategory call parameter {}", category);
            categoryResult = categoryRepository.save(category);
            log.debug("CategoryServiceImpl:createNewCategory entity result {}", categoryResult);
        } catch (Exception ex) {
            log.error(
                    "Exception occurred while persisting category to database, Exception message {}",
                    ex.getMessage()
            );
            throw new ServiceBusinessException(
                    "Exception occurred while save a new category",
                    ex.getCause()
            );
        }

        log.info("CategoryServiceImpl:createNewCategory execution end.");

        return categoryResult;
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
