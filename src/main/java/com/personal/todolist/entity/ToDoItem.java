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
    @Column(name="ID")
    private Long id;

    @Column(name="VALUE")
    private String value;

    @Enumerated(EnumType.STRING)
    @Column(name="STAGE", nullable = false)
    private Stage stage;

    @Column(name="UPDATED_TIMESTAMP")
    private Timestamp updatedTimestamp;

    @ManyToOne
    @JoinColumn(name = "LIST_ID", referencedColumnName = "ID")
    private ToDoList toDoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStage() {
        return stage.toString();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Timestamp getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    public void setUpdatedTimestamp(Timestamp updatedTimestamp) {
        this.updatedTimestamp = updatedTimestamp;
    }

    public ToDoList getToDoList() {
        return toDoList;
    }

    public void setToDoList(ToDoList toDoList) {
        this.toDoList = toDoList;
    }
}
