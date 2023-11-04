package edu.yacoubi.todobackend.contoller;

import edu.yacoubi.todobackend.dto.TodoDTO;
import edu.yacoubi.todobackend.dto.UserDTO;
import edu.yacoubi.todobackend.service.api.TodoServiceAPI;
import edu.yacoubi.todobackend.service.api.UserServiceAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoServiceAPI userService;

    @PostMapping("create")
    public ResponseEntity<TodoDTO> createNewUser(TodoDTO todo) {
        return new ResponseEntity<>(userService.createNewTodo(todo), HttpStatus.CREATED);
    }
}
