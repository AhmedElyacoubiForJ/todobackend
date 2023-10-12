package edu.yacoubi.todobackend.repository;

import edu.yacoubi.todobackend.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
