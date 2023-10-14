package edu.yacoubi.todobackend.service;

import edu.yacoubi.todobackend.model.AppUser;

import java.util.List;

public interface AppUserService {
    AppUser save(AppUser appUser);
    List<AppUser> findAll();
    AppUser findById(Long id);
    void delete(Long id);

    AppUser findAppUserByEmail(String email);
}
