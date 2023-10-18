package edu.yacoubi.todobackend.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

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

    public UserDTO(String firstName, String lastName, String email, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }
}
