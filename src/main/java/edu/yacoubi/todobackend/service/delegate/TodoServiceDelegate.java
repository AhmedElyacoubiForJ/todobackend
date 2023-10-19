package edu.yacoubi.todobackend.service.delegate;

import edu.yacoubi.todobackend.service.decktop.TodoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class TodoServiceDelegate {
    private final TodoService todoService;
}
