package edu.yacoubi.todobackend.service.delegate;

import edu.yacoubi.todobackend.service.decktop.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class UserServiceDelegate {
    private final UserService userService;

}
