package edu.yacoubi.todobackend.service.decktop.impl;

import edu.yacoubi.todobackend.exception.EntityNotFoundException;
import edu.yacoubi.todobackend.exception.InvalidArgumentException;
import edu.yacoubi.todobackend.exception.ServiceBusinessException;
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
            throw new ServiceBusinessException(
                    "Exception occurred while save a new appUser",
                    ex.getCause()
            );
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
        log.info("UserServiceImpl:getUserById execution started.");

        if (id == null) {
            log.error("Exception occurred while validating parameter, id is null");
            throw new InvalidArgumentException("id must not be null");
        }
        // Refactor TODO ServiceBusinessException


        AppUser appUser = userRepository.findById(id)
                .orElseThrow(() -> {
                    String message = "user with ID :  " + id + " not found";
                    EntityNotFoundException eNfE = new EntityNotFoundException(message);
                    log.error(
                            "Exception occurred while getting user by id {} from database, Exception message {}",
                            id,
                            eNfE.getMessage()
                    );
                    return eNfE;
                });

        log.info("UserServiceImpl:getUserById execution end.");
        return appUser;
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
        log.info("UserServiceImpl:getUserByEmail execution started.");

        if (email == null) {
            log.error("Exception occurred while validating parameter, email is null");
            throw new InvalidArgumentException("email must not be null");
        }

        if (email.isEmpty()) {
            log.error("email is empty");
            log.error("Exception occurred while validating parameter, email is empty");
            throw new InvalidArgumentException("email must not be empty");
        }

        log.debug("UserServiceImpl:getUserByEmail request parameters {}", email);

        AppUser appUserResponse = userRepository.findAppUserByEmail(email)
                .orElseThrow(
                        () -> {
                            String message = "user with EMAIL :  " + email + " not found";
                            EntityNotFoundException eNfE =
                                    new EntityNotFoundException(message);
                            log.error("error in getting user with EMAIL :  {}", email, eNfE);
                            return eNfE;
                        });
        log.debug("UserServiceImpl:getUserByEmail entity result {}", appUserResponse);

        log.info("UserServiceImpl:getUserByEmail execution end.");
        return appUserResponse;
    }
}
