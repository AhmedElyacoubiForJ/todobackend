package edu.yacoubi.todobackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "AppUser")
@Table(
        name = "app_user",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "app_user_email_unique",
                        columnNames = "email"
                )
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @SequenceGenerator(
            name = "app_user_sequence",
            sequenceName = "app_user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "app_user_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;

    @Column(
            name = "user_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String userName;

    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;

    @OneToMany(
            mappedBy = "appUser",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Category> categories = new ArrayList<>();

    public AppUser(String firstName,
                   String lastName,
                   String email,
                   String userName,
                   String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }
}
