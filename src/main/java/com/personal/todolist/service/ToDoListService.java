package com.personal.todolist.service;

import com.personal.todolist.entity.ToDoItem;
import com.personal.todolist.entity.ToDoList;
import com.personal.todolist.exceptions.ToDoListException;
import com.personal.todolist.repository.ToDoItemRepository;
import com.personal.todolist.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoListService {

    private ToDoListRepository toDoListRepository;

    private ToDoItemRepository toDoItemRepository;

    @Autowired
    public ToDoListService(ToDoListRepository toDoListRepository, ToDoItemRepository toDoItemRepository) {
        this.toDoListRepository = toDoListRepository;
        this.toDoItemRepository = toDoItemRepository;
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

    /**
     * returns List of oll the toDoList with given user Id
     * @param userId - id of a user that awns the
     */
    public List<ToDoList> getToDoListsByUserId(long userId) throws ToDoListException{
        return toDoListRepository.findAllByUserId(userId);
    }

    /**
     * returns a list of all items from a ToDoList
     * @param listId - id of a toDoList
     */
    public List<ToDoItem> getToDoItemsOwned(long listId) {
        return toDoListRepository.findById(listId).get().getToDoItemList();
    }

    /**
     * adds a toDoItem to the database
     * @param item
     * @return
     */
    public ToDoItem postToDoItemToTheList(ToDoItem item) {
        return toDoItemRepository.save(item);
    }
}
