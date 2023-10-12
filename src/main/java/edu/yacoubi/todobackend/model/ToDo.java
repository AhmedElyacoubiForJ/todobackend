package edu.yacoubi.todobackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "todos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDo {
    @Id
    @SequenceGenerator(
            name = "todo_sequence",
            sequenceName = "todo_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "todo_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "task",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String task;

    @Column(
            name = "description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;

    @Column(
            name = "start_date",
            nullable = false,
            columnDefinition = "TIMESTAMP WITH TIME ZONE"

    )
    private ZonedDateTime startDate;

    @Column(
            name = "done",
            nullable = false,
            columnDefinition = "boolean default false"
    )
    private boolean done;

    @Column(
            name = "favorite",
            nullable = false,
            columnDefinition = "boolean default false"
    )
    private boolean favorite;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
}
