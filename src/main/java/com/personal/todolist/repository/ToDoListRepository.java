package com.personal.todolist.repository;

import com.personal.todolist.entity.ToDoList;
import com.personal.todolist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
    public List<ToDoList> findAllByUserId(long userId);
}
