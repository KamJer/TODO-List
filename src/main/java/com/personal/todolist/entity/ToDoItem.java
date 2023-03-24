package com.personal.todolist.entity;

import com.personal.todolist.Stage;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "TODOITEM")
public class ToDoItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private long id;

    @Column(name="VALUE")
    private String value;

    @Enumerated(EnumType.STRING)
    @Column(name="STAGE", nullable = false)
    private Stage stage;

    @Column(name="UPDATED_TIMESTAMP")
    private LocalDateTime updatedTimestamp;

    @ManyToOne
    @JoinColumn(name = "LIST_ID", referencedColumnName = "ID")
    private ToDoList toDoList;

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public LocalDateTime getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    public void setUpdatedTimestamp(LocalDateTime updatedTimestamp) {
        this.updatedTimestamp = updatedTimestamp;
    }
}
