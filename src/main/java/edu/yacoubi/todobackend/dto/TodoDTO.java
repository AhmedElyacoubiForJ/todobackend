package edu.yacoubi.todobackend.dto;

import edu.yacoubi.todobackend.model.Category;

import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;

public class TodoDTO {

    @NotBlank(message = "task shouldn't be NULL OR EMPTY")
    private String task;

    @NotBlank(message = "description shouldn't be NULL OR EMPTY")
    private String description;

    private ZonedDateTime startDate;

    private boolean done;

    private boolean favorite;

    @NotBlank(message = "category shouldn't be NULL OR EMPTY")
    private CategoryDTO categoryDTO;
}
