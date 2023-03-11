package com.personal.todolist.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "TODOITEM")
public class ToDoItem {

    public enum Stage {
        TODO,
        IN_PROGRESS,
        DONE
    }
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="value")
    private String value;

    @Enumerated(EnumType.STRING)
    @Column(name="stage", nullable = false)
    private Stage stage;

    @Column(name="updated_timestamp")
    private Timestamp updatedTimestamp;

    @ManyToOne
    @JoinColumn(name = "list_id", referencedColumnName = "id")
    private ToDoList toDoList;
}
