package edu.yacoubi.todobackend.service.api;

import edu.yacoubi.todobackend.dto.TodoDTO;

import java.util.List;

public interface TodoServiceAPI {
    TodoDTO createNewTodo(TodoDTO todoDTO);
    List<TodoDTO> getAllTodos();
    TodoDTO getTodoById(Long id);
    List<TodoDTO> getTodosByCategoryId(Long categoryId);
    void deleteTodo(Long id);
}
