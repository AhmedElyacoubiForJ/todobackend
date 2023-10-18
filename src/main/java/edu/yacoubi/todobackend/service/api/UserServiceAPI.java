package edu.yacoubi.todobackend.service.api;

import edu.yacoubi.todobackend.dto.UserDTO;

import java.util.List;
// use to develop api application
public interface UserServiceAPI {
    UserDTO createNewUser(UserDTO userDTO);
    List<UserDTO> getUsers();
    UserDTO getUserById(Long id);
    void deleteUserById(Long id);
    UserDTO getUserByEmail(String email);
    UserDTO login(UserDTO userDTO);
}