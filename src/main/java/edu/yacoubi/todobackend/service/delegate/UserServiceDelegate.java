package edu.yacoubi.todobackend.service.delegate;

import edu.yacoubi.todobackend.service.decktop.UserService;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class UserServiceDelegate {
    private final UserService userService;

    public UserServiceDelegate(UserService userService) {
        this.userService = userService;
    }
}
