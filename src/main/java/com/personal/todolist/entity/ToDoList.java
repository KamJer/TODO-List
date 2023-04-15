package com.personal.todolist.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TODOLIST")
public class ToDoList {

    public ToDoList(long userId, String name) {
        this.userId = userId;
        this.updatedTimestamp = LocalDateTime.now();
        this.name = name;
    }
    public ToDoList(long id, long userId, String name) {
        this.id = id;
        this.userId = userId;
        this.updatedTimestamp = LocalDateTime.now();
        this.name = name;
    }

    public ToDoList() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="USER_ID")
    private Long userId;

    @Column(name="NAME")
    private String name;

    @Column(name="UPDATED_TIMESTAMP", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime updatedTimestamp;

    @OneToMany(mappedBy = "toDoList", fetch = FetchType.EAGER)
    private List<ToDoItem> toDoItemList;

    @Override
    public int hashCode() {
        return  (id.intValue()) + userId.intValue() * name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "ID: " + id + ", USER_ID: " + userId +  ", NAME: " + name + ", TIME_STAMP:" + updatedTimestamp;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    public void setUpdatedTimestamp(LocalDateTime updatedTimestamp) {
        this.updatedTimestamp = updatedTimestamp;
    }

    public List<ToDoItem> getToDoItemList() {
        return toDoItemList;
    }

    public void setToDoItemList(List<ToDoItem> toDoItemList) {
        this.toDoItemList = toDoItemList;
    }
}
