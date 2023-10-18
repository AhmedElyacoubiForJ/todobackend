package edu.yacoubi.todobackend.service.decktop.impl;

import edu.yacoubi.todobackend.exception.EntityNotFoundException;
import edu.yacoubi.todobackend.exception.InvalidArgumentException;
import edu.yacoubi.todobackend.exception.UserServiceBusinessException;
import edu.yacoubi.todobackend.model.AppUser;
import edu.yacoubi.todobackend.repository.UserRepository;
import edu.yacoubi.todobackend.service.decktop.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public AppUser createNewUser(AppUser appUser) {
        log.info("UserServiceImpl:createNewUser execution started.");

        if (appUser == null) {
            log.error("Exception occurred while validating parameter, appUser is null");
            throw new InvalidArgumentException("AppUser must not be null");
        }

        AppUser userResult;
        try {
            log.debug("UserServiceImpl:createNewUser call parameter {}", appUser);
            userResult = userRepository.save(appUser);
            log.debug("UserServiceImpl:createNewUser entity result {}", userResult);
        } catch (Exception ex) {
            log.error(
                    "Exception occurred while persisting appUser to database, Exception message {}",
                    ex.getMessage()
            );
            throw new UserServiceBusinessException("Exception occurred while save a new appUser");
        }

        log.info("UserServiceImpl:createNewUser execution end.");
        return userResult;
    }

    @Override
    public List<AppUser> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public AppUser getUserById(Long id) {
        log.info("UserServiceImpl findById...");

        if (id == null) {
            log.error("id is null");
            throw new InvalidArgumentException("user id must not be null");
        }

        return userRepository.findById(id)
                .orElseThrow(() -> {
                    String message = "user with ID :  " + id + " not found";
                    EntityNotFoundException eNfE =
                            new EntityNotFoundException(message);
                    log.error("error in getting user with ID :  {}", id, eNfE);
                    return eNfE;
                }
        );
    }

    @Override
    public void deleteUserById(Long id) {
        log.info("UserServiceImpl delete...");

        if (id == null) {
            log.error("id is null");
            throw new InvalidArgumentException("id must not be null");
        }
        userRepository.deleteById(id);
    }

    @Override
    public AppUser getUserByEmail(String email) {
        // TODO to improve
        log.info("UserServiceImpl:findAppUserByEmail execution started.");

        if (email == null) {
            log.error("email is null");
            throw new InvalidArgumentException("email must not be null");
        }

        if (email.isEmpty()) {
            log.error("email is empty");
            throw new InvalidArgumentException("email must not be empty");
        }

        log.debug("ProductService:createNewProduct request parameters {}", email);


        AppUser appUserResponse = userRepository.findAppUserByEmail(email)
                .orElseThrow(
                        () -> {
                            String message = "user with EMAIL :  " + email + " not found";
                            EntityNotFoundException eNfE =
                                    new EntityNotFoundException(message);
                            log.error("error in getting user with EMAIL :  {}", email, eNfE);
                            return eNfE;
                        });
        log.info("UserServiceImpl:findAppUserByEmail execution ended.");
        return appUserResponse;
    }
}
