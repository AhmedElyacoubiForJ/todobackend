package edu.yacoubi.todobackend.repository;

import edu.yacoubi.todobackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
