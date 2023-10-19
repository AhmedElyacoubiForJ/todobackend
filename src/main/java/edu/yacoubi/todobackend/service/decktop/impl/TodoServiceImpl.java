package edu.yacoubi.todobackend.service.decktop.impl;

import edu.yacoubi.todobackend.exception.InvalidArgumentException;
import edu.yacoubi.todobackend.exception.ServiceBusinessException;
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
    public Todo createNewTodo(Todo todo) {
        log.info("TodoServiceImpl:createNewTodo execution started.");

        if (todo == null) {
            log.error("Exception occurred while validating parameter, todo is null");
            throw new InvalidArgumentException("todo must not be null");
        }

        Todo todoResult;
        try {
            log.debug("TodoServiceImpl:createNewTodo call parameter {}", todo);
            todoResult = todoRepository.save(todo);
            log.debug("TodoServiceImpl:createNewTodo entity result {}", todoResult);
        } catch (Exception ex) {
            log.error(
                    "Exception occurred while persisting todo to database, Exception message {}",
                    ex.getMessage()
            );
            throw new ServiceBusinessException(
                    "Exception occurred while save a new todo",
                    ex.getCause()
            );
        }

        log.info("TodoServiceImpl:createNewTodo execution end.");
        return todoResult;
    }

    @Override
    public List<Todo> getTodos() {
        return null;
    }

    @Override
    public Todo getTodoById(Long id) {
        return null;
    }

    @Override
    public List<Todo> getTodosByCategoryId(Long categoryId) {
        return null;
    }

    @Override
    public void deleteTodoById(Long id) {

    }
}
