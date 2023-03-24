package com.personal.todolist.service;

import com.personal.todolist.entity.ToDoList;
import com.personal.todolist.exceptions.ToDoListException;
import com.personal.todolist.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ToDoListService {

    private ToDoListRepository toDoListRepository;

    @Autowired
    public ToDoListService(ToDoListRepository toDoListRepository) {
        this.toDoListRepository = toDoListRepository;
    }

    /**
     * Adds new ToDoList to the database
     * @param toDoList - data to add
     * @throws ToDoListException
     */
    public ToDoList postToDoList(ToDoList toDoList) throws ToDoListException {
        return toDoListRepository.save(toDoList);
    }

    /**
     * Finds ToDoList with its id number
     * @param id - id number of a ToDoList to find
     * @return ToDoList with specific id number
     * @throws ToDoListException
     */
    public ToDoList getToDoListById(long id) throws ToDoListException {
        Optional<ToDoList> toDoList = toDoListRepository.findById(id);
        if (toDoList.isEmpty())
            throw new ToDoListException("No such ToDoList found");
        return toDoList.get();
    }

    /**
     * updates ToDoList
     * @param toDoList - data to update
     * @param id - id of a list to update
     * @throws ToDoListException
     */
    public ToDoList updateToDoList(ToDoList toDoList, long id) throws ToDoListException {
        Optional<ToDoList> testToDoList = toDoListRepository.findById(id);
        if (testToDoList.isEmpty())
            throw new ToDoListException("No such ToDoList found");

        return testToDoList.map(foundToDoList -> {
            foundToDoList.setName(toDoList.getName());
            foundToDoList.setUpdatedTimestamp(toDoList.getUpdatedTimestamp());
            return toDoListRepository.save(foundToDoList);
        }).get();
    }

    /**
     * deletes ToDoList from database
     * @param id - id of a list to delete
     * @throws ToDoListException
     */
    public void deleteToDoList(long id) {
        toDoListRepository.deleteById(id);
    }
}
