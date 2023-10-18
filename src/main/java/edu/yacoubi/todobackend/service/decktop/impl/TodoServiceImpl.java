package edu.yacoubi.todobackend.service.decktop.impl;

import edu.yacoubi.todobackend.model.Todo;
import edu.yacoubi.todobackend.repository.TodoRepository;
import edu.yacoubi.todobackend.service.decktop.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoServiceImpl implements TodoService {

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
