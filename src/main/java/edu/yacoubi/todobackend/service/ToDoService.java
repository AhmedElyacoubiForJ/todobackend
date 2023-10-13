package edu.yacoubi.todobackend.service;

import edu.yacoubi.todobackend.model.ToDo;

import java.util.List;

public interface ToDoService {
    void save(ToDo todoDto);
    List<ToDo> findAll();
    ToDo findById(Long id);
    List<ToDo> findByCategory(Long categoryId);
    void delete(Long id);
}
