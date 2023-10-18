package edu.yacoubi.todobackend.service.api.impl;

import edu.yacoubi.todobackend.dto.UserDTO;
import edu.yacoubi.todobackend.model.AppUser;
import edu.yacoubi.todobackend.service.api.UserServiceAPI;
import edu.yacoubi.todobackend.service.delegate.UserServiceDelegate;
import edu.yacoubi.todobackend.util.EntityValueMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceAPIImpl implements UserServiceAPI {
    private final UserServiceDelegate userServiceDelegate;

    @Override
    public UserDTO createNewUser(UserDTO userDTO) {
        log.info("UserServiceAPIImpl:createNewUser execution started.");

        log.debug("UserServiceAPIImpl:createNewUser request parameter {}", userDTO);
        ModelMapper modelMapper = new ModelMapper();
        // test
        AppUser toAppUser = modelMapper.map(userDTO, AppUser.class);

        //AppUser toAppUser = EntityValueMapper.convertToEntity(userDTO);

        log.debug("UserServiceAPIImpl:UserServiceDelegate call start");
        AppUser appUserResult = userServiceDelegate
                .getUserService()
                .createNewUser(toAppUser);
        log.debug("UserServiceAPIImpl:UserServiceDelegate call return");

        //UserDTO toUserDTOResult  = EntityValueMapper.convertToDTO(appUserResult);
        UserDTO toUserDTOResult = new ModelMapper().map(appUserResult, UserDTO.class);

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
