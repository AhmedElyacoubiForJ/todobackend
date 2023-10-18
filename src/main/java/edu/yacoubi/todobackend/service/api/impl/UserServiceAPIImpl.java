package edu.yacoubi.todobackend.service.api.impl;

import edu.yacoubi.todobackend.dto.UserDTO;
import edu.yacoubi.todobackend.exception.UserServiceBusinessException;
import edu.yacoubi.todobackend.model.AppUser;
import edu.yacoubi.todobackend.repository.UserRepository;
import edu.yacoubi.todobackend.service.api.UserServiceAPI;
import edu.yacoubi.todobackend.service.delegate.UserServiceDelegate;
import edu.yacoubi.todobackend.util.EntityValueMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceAPIImpl implements UserServiceAPI {

    private final UserRepository repository;
    private final UserServiceDelegate userServiceDelegate;

    @Override
    public UserDTO createNewUser(UserDTO userDTO) {
        log.info("UserServiceAPIImpl:createNewUser execution started.");

        log.debug("UserServiceAPIImpl:createNewUser request parameter {}", userDTO);

        AppUser toAppUser = EntityValueMapper.convertToEntity(userDTO);


        AppUser appUserResult;
//        try {
//            log.debug("UserServiceAPIImpl:createNewUser request parameters {}", toAppUser);
//
//            appUserResult = repository.save(toAppUser);
//
//            log.debug("UserServiceAPIImpl:createNewUser result parameters {}", appUserResult);
//        } catch (Exception ex) {
//            log.error(
//                    "Exception occurred while persisting appUser to database, Exception message {}",
//                    ex.getMessage()
//            );
//            throw new UserServiceBusinessException("Exception occurred while save a new appUser");
//        }

        log.debug("UserServiceAPIImpl:UserServiceDelegate call start");
        appUserResult = userServiceDelegate.getUserService().createNewUser(toAppUser);
        log.debug("UserServiceAPIImpl:UserServiceDelegate call return");

        UserDTO toUserDTOResult  = EntityValueMapper.convertToDTO(appUserResult);

        log.info("UserServiceAPIImpl:createNewUser execution end.");

        return toUserDTOResult;
    }

    @Override
    public List<UserDTO> getUsers() {
        return null;
    }

    @Override
    public UserDTO getUserById(Long id) {
        return null;
    }

    @Override
    public void deleteUserById(Long id) {
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return null;
    }

    @Override
    public UserDTO login(UserDTO user) {
        return null;
    }
}
