package edu.yacoubi.todobackend.service;

import edu.yacoubi.todobackend.EntityNotFoundException;
import edu.yacoubi.todobackend.model.AppUser;
import edu.yacoubi.todobackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public AppUser save(AppUser appUser) {
        log.info("UserServiceImpl save...");

        if (appUser == null) {
            log.error("user is null");
            throw new IllegalArgumentException();
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
            throw new IllegalArgumentException();
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
            throw new IllegalArgumentException();
        }
        userRepository.deleteById(id);
    }

    @Override
    public AppUser findAppUserByEmail(String email) {
        log.info("UserServiceImpl findAppUserByEmail...");

        if (email == null) {
            log.error("email is null");
            throw new IllegalArgumentException();
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
