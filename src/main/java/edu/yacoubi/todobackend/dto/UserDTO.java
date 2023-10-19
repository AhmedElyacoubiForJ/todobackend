package edu.yacoubi.todobackend.dto;

import edu.yacoubi.todobackend.model.AppUser;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class UserDTO {

    @NotBlank(message = "user id name shouldn't be NULL OR EMPTY")
    private Long id;

    @NotBlank(message = "user first name shouldn't be NULL OR EMPTY")
    private String firstName;

    @NotBlank(message = "user last name shouldn't be NULL OR EMPTY")
    private String lastName;

    @NotBlank(message = "email shouldn't be NULL OR EMPTY")
    private String email;

    @NotBlank(message = "username shouldn't be NULL OR EMPTY")
    private String userName;

    @NotBlank(message = "password shouldn't be NULL OR EMPTY")
    private String password;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<CategoryDTO> categories = new ArrayList<>();

    public UserDTO(String firstName,
                   String lastName,
                   String email,
                   String userName,
                   String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public static AppUser toEntity(UserDTO userDTO) {
        final AppUser user = new AppUser();

        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setCategories(
                userDTO.getCategories() != null ?
                        userDTO.getCategories().stream()
                                .map(CategoryDTO::toEntity)
                                .collect(Collectors.toList())
                        : null
        );

        return user;
    }

    public static UserDTO fromEntity(AppUser user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUserName())
                .password(user.getPassword())
                .email(user.getEmail())
                .categories(
                        user.getCategories() != null
                                ? user.getCategories().stream()
                                        .map(CategoryDTO::fromEntity)
                                        .collect(Collectors.toList())
                                : null
                )
                .build();
    }
}
