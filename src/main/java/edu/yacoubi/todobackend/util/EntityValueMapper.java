package edu.yacoubi.todobackend.util;

import edu.yacoubi.todobackend.dto.CategoryDTO;
import edu.yacoubi.todobackend.dto.UserDTO;
import edu.yacoubi.todobackend.model.AppUser;
import edu.yacoubi.todobackend.model.Category;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@UtilityClass
public class EntityValueMapper {


//    public static AppUser convertToEntity(UserDTO userDTO) {
//        log.info("EntityValueMapper:convertToEntity start");
//        log.debug("EntityValueMapper:convertToEntity request parameter {}", userDTO);
//
//        AppUser appUser = new AppUser();
//
//        appUser.setId(userDTO.getId());
//        appUser.setFirstName(userDTO.getFirstName());
//        appUser.setLastName(userDTO.getLastName());
//        appUser.setUserName(userDTO.getUserName());
//        appUser.setEmail(userDTO.getEmail());
//        appUser.setPassword(userDTO.getPassword());
//        // fake TODO
//        List<Category> categories = Collections.emptyList();
//
//        appUser.setCategories(categories);
//
//        log.debug("EntityValueMapper:convertToEntity convert result {}", appUser);
//
//        log.info("EntityValueMapper:convertToEntity end.");
//        return appUser;
//    }
//
//    /*public static Category convertTooEntity(CategoryDTO categoryDTO) {
//        Category category = new Category();
//
//        //categoryDTO.getUserDTO()
//
//        //category.setAppUser();
//        category.setId(categoryDTO.getId());
//        category.setName(categoryDTO.getName());
//        category.setDescription(categoryDTO.getDescription());
//
//        return category;
//    }*/
//
//    public static UserDTO convertToDTO(AppUser appUser) {
//        log.info("EntityValueMapper:convertToDTO start");
//        log.debug("EntityValueMapper:convertToDTO request parameter {}", appUser);
//        List<Category> categories = Collections.emptyList();
//
//
//        UserDTO userDTO = UserDTO.builder()
//                .id(appUser.getId())
//                .firstName(appUser.getFirstName())
//                .lastName(appUser.getLastName())
//                .userName(appUser.getUserName())
//                .password(appUser.getPassword())
//                .email(appUser.getEmail())
//                .categories(new ArrayList<CategoryDTO>()) // fake TODO
//                .build();
//
//        log.debug("EntityValueMapper:convertToDTO convert result {}", userDTO);
//
//        log.info("EntityValueMapper:convertToDTO end.");
//
//        return userDTO;
//    }

}
