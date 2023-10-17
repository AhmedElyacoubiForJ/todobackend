package edu.yacoubi.todobackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Category")
@Table(name = "category")
//@Data
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class Category {
    @Id
    @SequenceGenerator(
            name = "category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "category_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name = "description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Todo> todos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(
            name = "app_user_id",
            nullable = false,
            referencedColumnName = "id",
            // readability
            // to rename a generated random name created by spring data framework
            // so, it's the best practice to have a full control about our application
            foreignKey = @ForeignKey(
                    name = "app_user_category_fk"
            )
    )
    private AppUser appUser;

    public Category(String name, String description, AppUser appUser) {
        this.name = name;
        this.description = description;
        this.appUser = appUser;
    }
}
