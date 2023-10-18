package edu.yacoubi.todobackend.service.decktop.impl;

import edu.yacoubi.todobackend.model.Category;
import edu.yacoubi.todobackend.model.Todo;
import edu.yacoubi.todobackend.repository.CategoryRepository;
import edu.yacoubi.todobackend.repository.TodoRepository;
import edu.yacoubi.todobackend.service.decktop.CategoryService;
import edu.yacoubi.todobackend.service.decktop.TodoService;
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

    @Service
    @RequiredArgsConstructor
    @Slf4j
    public static class TodoServiceImpl implements TodoService {

        private final TodoRepository todoRepository;

        @Override
        public void save(Todo todo) {

        }

        @Override
        public List<Todo> findAll() {
            return null;
        }

        @Override
        public Todo findById(Long id) {
            return null;
        }

        @Override
        public List<Todo> findByCategory(Long categoryId) {
            return null;
        }

        @Override
        public void delete(Long id) {

        }
    }
}
