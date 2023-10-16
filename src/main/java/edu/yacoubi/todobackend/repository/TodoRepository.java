package edu.yacoubi.todobackend.repository;

import edu.yacoubi.todobackend.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository
        extends JpaRepository<Todo, Long> {

    //List<ToDo> findTodosByCategoryId(Long categoryId);
}
