package edu.yacoubi.todobackend.service.delegate;

import edu.yacoubi.todobackend.service.decktop.CategoryService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class CategoryServiceDelegate {
    private final CategoryService categoryService;
}
