package edu.yacoubi.todobackend.dto;

import edu.yacoubi.todobackend.model.AppUser;
import edu.yacoubi.todobackend.model.Todo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CategoryDTO {

    @NotBlank(message = "category id shouldn't be NULL OR EMPTY")
    private Long id;

    @NotBlank(message = "category name shouldn't be NULL OR EMPTY")
    private String name;

    @NotBlank(message = "category description shouldn't be NULL OR EMPTY")
    private String description;

    private List<TodoDTO> todos = new ArrayList<>();

    @NotBlank(message = "user shouldn't be NULL OR EMPTY")
    private UserDTO userDTO;

    public CategoryDTO(String name, String description, UserDTO userDTO) {
        this.name = name;
        this.description = description;
        this.todos = todos;
        this.userDTO = userDTO;
    }
}
