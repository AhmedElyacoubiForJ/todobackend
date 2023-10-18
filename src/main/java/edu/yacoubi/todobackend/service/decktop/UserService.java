package edu.yacoubi.todobackend.service.decktop;

import edu.yacoubi.todobackend.model.AppUser;

import java.util.List;

// use to develop desktop application
public interface UserService {
    AppUser createNewUser(AppUser appUser);
    List<AppUser> getUsers();
    AppUser getUserById(Long id);
    void deleteUserById(Long id);
    AppUser getUserByEmail(String email);
}
