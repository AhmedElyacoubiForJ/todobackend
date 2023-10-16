package edu.yacoubi.todobackend.service;

import edu.yacoubi.todobackend.model.Todo;

import java.util.List;

public interface TodoService {
    void save(Todo todo);
    List<Todo> findAll();
    Todo findById(Long id);
    List<Todo> findByCategory(Long categoryId);
    void delete(Long id);
}
