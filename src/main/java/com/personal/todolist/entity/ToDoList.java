package com.personal.todolist.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "TODOLIST")
public class ToDoList {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="updated_timestamp")
    private Timestamp updatedTimestamp;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User User;
}
