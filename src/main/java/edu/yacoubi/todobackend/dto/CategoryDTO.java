package edu.yacoubi.todobackend.dto;

import edu.yacoubi.todobackend.model.Category;
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

    public static Category toEntity(CategoryDTO categoryDTO) {
        Category category = new Category();

        category.setAppUser(UserDTO.toEntity(categoryDTO.getUserDTO()));
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        return category;
    }

    public static CategoryDTO fromEntity(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .todos(
                        category.getTodos() != null ?
                                category.getTodos().stream().map(TodoDTO::fromEntity).collect(Collectors.toList()) :
                                null
                )
                .build();
    }
}
