package edu.yacoubi.todobackend.service.decktop;

import edu.yacoubi.todobackend.model.Todo;

import java.util.List;

public interface TodoService {
    Todo createNewTodo(Todo todo);
    List<Todo> getTodos();
    Todo getTodoById(Long id);
    List<Todo> getTodosByCategoryId(Long categoryId);
    void deleteTodoById(Long id);
}
