package edu.yacoubi.todobackend.repository;

import edu.yacoubi.todobackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository
        extends JpaRepository<Category, Long> {

    //List<Category> findCategoryByUserId(Long userId);
}
