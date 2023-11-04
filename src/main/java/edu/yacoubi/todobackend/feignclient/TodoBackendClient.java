package edu.yacoubi.todobackend.feignclient;

import edu.yacoubi.todobackend.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        value = "todobackend",
        url = "http://localhost:9090/api/v1/users/"
)
public interface TodoBackendClient {

    @GetMapping("")
    List<UserDTO> getUsers();

    @GetMapping("user/{userId}")
    UserDTO getUserDTO(@PathVariable("userId") Integer userId);
}
