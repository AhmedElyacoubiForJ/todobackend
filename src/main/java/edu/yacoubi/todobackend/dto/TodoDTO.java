package edu.yacoubi.todobackend.dto;

import edu.yacoubi.todobackend.model.Todo;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class TodoDTO {

    @NotBlank(message = "todo id name shouldn't be NULL OR EMPTY")
    private Long id;

    @NotBlank(message = "task shouldn't be NULL OR EMPTY")
    private String task;

    @NotBlank(message = "description shouldn't be NULL OR EMPTY")
    private String description;

    private ZonedDateTime startDate;

    private boolean done;

    private boolean favorite;

    @NotBlank(message = "category shouldn't be NULL OR EMPTY")
    private CategoryDTO categoryDTO;

    public static Todo toEntity(TodoDTO todoDTO) {
        final Todo todo = new Todo();
        todo.setId(todoDTO.getId());
        todo.setTask(todoDTO.getTask());
        todo.setDescription(todoDTO.getDescription());
        todo.setDone(todoDTO.isDone());
        todo.setFavorite(todoDTO.isFavorite());
        todo.setStartDate(ZonedDateTime.now());
        todo.setCategory(CategoryDTO.toEntity(todoDTO.getCategoryDTO()));

        return todo;
    }

    public static TodoDTO fromEntity(Todo todo) {
        return TodoDTO.builder()
                .id(todo.getId())
                .task(todo.getTask())
                .description(todo.getDescription())
                .startDate(todo.getStartDate())
                .done(todo.isDone())
                .favorite(todo.isFavorite())
                .build();
    }
}
