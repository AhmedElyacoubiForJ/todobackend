package edu.yacoubi.todobackend.service.impl;

import edu.yacoubi.todobackend.exception.EntityNotFoundException;
import edu.yacoubi.todobackend.exception.InvalidArgumentException;
import edu.yacoubi.todobackend.model.AppUser;
import edu.yacoubi.todobackend.repository.UserRepository;
import edu.yacoubi.todobackend.service.UserService;
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
    public AppUser save(AppUser appUser) {
        log.info("UserServiceImpl save...");

        if (appUser == null) {
            log.error("user is null");
            throw new InvalidArgumentException("AppUser must not be null");
        }

        return userRepository.save(appUser);
    }

    @Override
    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public AppUser findById(Long id) {
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
    public void delete(Long id) {
        log.info("UserServiceImpl delete...");

        if (id == null) {
            log.error("id is null");
            throw new InvalidArgumentException("id must not be null");
        }
        userRepository.deleteById(id);
    }

    @Override
    public AppUser findAppUserByEmail(String email) {
        log.info("UserServiceImpl findAppUserByEmail...");

        if (email == null) {
            log.error("email is null");
            throw new InvalidArgumentException("email must not be null");
        }

        if (email.isEmpty()) {
            log.error("email is empty");
            throw new InvalidArgumentException("email must not be empty");
        }

        return userRepository.findAppUserByEmail(email)
                .orElseThrow(
                        () -> {
                            String message = "user with EMAIL :  " + email + " not found";
                            EntityNotFoundException eNfE =
                                    new EntityNotFoundException(message);
                            log.error("error in getting user with EMAIL :  {}", email, eNfE);
                            return eNfE;
                        });
    }
}
