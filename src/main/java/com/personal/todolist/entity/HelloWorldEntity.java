package com.personal.todolist.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "HelloWorld")
public class HelloWorldEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="value")
    private String value;

    public HelloWorldEntity() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }
}
