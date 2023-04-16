package com.personal.todolist.repository;

import com.personal.todolist.entity.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {
    public void deleteByListId(long listId);
}
