package edu.yacoubi.todobackend.service;

import edu.yacoubi.todobackend.EntityNotFoundException;
import edu.yacoubi.todobackend.model.AppUser;
import edu.yacoubi.todobackend.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;

    @Override
    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @Override
    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser findById(Long id) {
        log.info("call findById...");

        if (id == null) {
            log.error("id is null");
            throw new IllegalArgumentException();
        }

        return appUserRepository.findById(id)
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
    }

    @Override
    public AppUser findAppUserByEmail(String email) {
        log.info("call findAppUserByEmail...");

        if (email == null) {
            log.error("email is null");
            throw new IllegalArgumentException();
        }
        return appUserRepository.findAppUserByEmail(email)
                .orElseThrow(() -> {
                            String message = "user with EMAIL :  " + email + " not found";
                            EntityNotFoundException eNfE =
                                    new EntityNotFoundException(message);
                            log.error("error in getting user with EMAIL :  {}", email, eNfE);
                            return eNfE;
                        }
                );
    }
}
