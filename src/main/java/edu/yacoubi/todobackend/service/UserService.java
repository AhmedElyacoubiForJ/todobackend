package edu.yacoubi.todobackend.service;

import edu.yacoubi.todobackend.model.AppUser;

import java.util.List;

public interface UserService {
    void save(AppUser appUser);
    List<AppUser> findAll();
    AppUser findById(Long id);
    void delete(Long id);
}
