package edu.yacoubi.todobackend.service.api.impl;

import edu.yacoubi.todobackend.dto.TodoDTO;
import edu.yacoubi.todobackend.model.Todo;
import edu.yacoubi.todobackend.service.api.TodoServiceAPI;
import edu.yacoubi.todobackend.service.delegate.TodoServiceDelegate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoServiceAPIImpl implements TodoServiceAPI {

    private final TodoServiceDelegate todoServiceDelegate;

    @Override
    public TodoDTO createNewTodo(TodoDTO todoDTO) {

        log.info("TodoServiceAPIImpl:createNewTodo execution started.");

        log.debug("TodoServiceAPIImpl:createNewTodo request parameter {}", todoDTO);

        Todo toTodo = TodoDTO.toEntity(todoDTO);

        log.debug("TodoServiceAPIImpl:TodoServiceDelegate call start");
        Todo toTodoResult = todoServiceDelegate
                .getTodoService()
                .createNewTodo(toTodo);
        log.debug("TodoServiceAPIImpl:TodoServiceDelegate call return");

        TodoDTO toTodoDTOResult = TodoDTO.fromEntity(toTodo);

        log.info("TodoServiceAPIImpl:createNewTodo execution end.");
        return toTodoDTOResult;
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
