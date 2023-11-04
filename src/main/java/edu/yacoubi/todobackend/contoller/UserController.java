package edu.yacoubi.todobackend.contoller;

import edu.yacoubi.todobackend.dto.UserDTO;
import edu.yacoubi.todobackend.service.api.UserServiceAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/users/")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceAPI userService;

//    @GetMapping
//    public ResponseEntity<List<UserDTO>> getUsers(){
//        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
//    }

    @GetMapping
    public List<UserDTO> getUsers(){
        return userService.getUsers();
    }
}
