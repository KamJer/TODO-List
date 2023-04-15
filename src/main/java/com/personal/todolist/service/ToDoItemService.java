package com.personal.todolist.service;


import com.personal.todolist.entity.ToDoItem;
import com.personal.todolist.exceptions.ToDoListException;
import com.personal.todolist.repository.ToDoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ToDoItemService {
    private ToDoItemRepository toDoItemRepository;

    @Autowired
    public ToDoItemService(ToDoItemRepository toDoItemRepository) {
        this.toDoItemRepository = toDoItemRepository;
    }

    /**
     * Adds new ToDoItem to the database
     * @param toDoItem - data to add
     * @throws ToDoListException
     */
    public ToDoItem postToDoItem(ToDoItem toDoItem) throws ToDoListException {
        return toDoItemRepository.save(toDoItem);
    }

    /**
     * Finds ToDoItem with its id number
     * @param id - id number of a ToDoItem to find
     * @return ToDoItem with specific id number
     * @throws ToDoListException
     */
    public ToDoItem getToDoListById(long id) throws ToDoListException {
        Optional<ToDoItem> toDoItem = toDoItemRepository.findById(id);
        if (toDoItem.isEmpty())
            throw new ToDoListException("No such ToDoItem found");
        return toDoItem.get();
    }

    /**
     * updates ToDoItem
     * @param toDoItem - data to update
     * @param id - id of a ToDoItem to update
     * @throws ToDoListException
     */
    public ToDoItem updateToDoList(ToDoItem toDoItem, long id) throws ToDoListException {
        Optional<ToDoItem> testToDoItem = toDoItemRepository.findById(id);
        if (!testToDoItem.isPresent())
            throw new ToDoListException("No such ToDoItem found");

        return testToDoItem.map(foundToDoItem -> {
            foundToDoItem.setValue(toDoItem.getValue());
            foundToDoItem.setStage(toDoItem.getStage());
            foundToDoItem.setUpdatedTimestamp(toDoItem.getUpdatedTimestamp());
            return toDoItemRepository.save(foundToDoItem);
        }).get();
    }

    /**
     * deletes ToDoItem from database
     * @param id - id of a ToDoItem to delete
     */
    public void deleteToDoList(long id) {
        toDoItemRepository.deleteById(id);
    }
}
