package edu.yacoubi.todobackend.service.api.impl;

import edu.yacoubi.todobackend.dto.TodoDTO;
import edu.yacoubi.todobackend.repository.TodoRepository;
import edu.yacoubi.todobackend.service.api.TodoServiceAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceAPIImpl implements TodoServiceAPI {

    private final TodoRepository repository;

    @Override
    public void createNewTodo(TodoDTO todoDTO) {

    }

    @Override
    public List<TodoDTO> getAllTodos() {
        return null;
    }

    @Override
    public TodoDTO getTodoById(Long id) {
        return null;
    }

    @Override
    public List<TodoDTO> getTodosByCategoryId(Long categoryId) {
        return null;
    }

    @Override
    public void deleteTodo(Long id) {

    }
}
