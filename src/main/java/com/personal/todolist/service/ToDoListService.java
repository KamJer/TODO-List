package com.personal.todolist.service;

import com.personal.todolist.entity.ToDoItem;
import com.personal.todolist.entity.ToDoList;
import com.personal.todolist.entity.User;
import com.personal.todolist.exceptions.ToDoListException;
import com.personal.todolist.exceptions.UserException;
import com.personal.todolist.repository.ToDoItemRepository;
import com.personal.todolist.repository.ToDoListRepository;
import com.personal.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoListService {

    private UserRepository userRepository;
    private ToDoListRepository toDoListRepository;

    private ToDoItemRepository toDoItemRepository;

    @Autowired
    public ToDoListService(UserRepository userRepository, ToDoListRepository toDoListRepository, ToDoItemRepository toDoItemRepository) {
        this.userRepository = userRepository;
        this.toDoListRepository = toDoListRepository;
        this.toDoItemRepository = toDoItemRepository;
    }

    /**
     * Adds new ToDoList to the database
     * @param toDoList - data to add
     * @throws ToDoListException
     */
    public ToDoList postToDoList(ToDoList toDoList) throws UserException {
        if (!userRepository.existsById(toDoList.getUserId())){
            throw new UserException("No such User found");
        }
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
     */
    public void deleteToDoList(long id) {
        // delete ToDoList with the given id from the repository
        toDoListRepository.deleteById(id);
        // find all ToDoItems in the repository
        List<ToDoItem> itemList = toDoItemRepository.findAll();
        // iterate through all found ToDoItems
        for (int i = 0; i < itemList.size(); i++) {
            // if the ToDoList to which the ToDoItem belongs has an id equal to the given id
            if (itemList.get(i).getToDoList().getId() == id) {
                // delete this ToDoItem from the repository
                toDoItemRepository.deleteById(itemList.get(i).getId());
            }
        }
    }

    /**
     * returns List of oll the toDoList with given user Id
     * @param userId - id of a user that awns the
     * @throws ToDoListException
     */
    public List<ToDoList> getToDoListsByUserId(long userId) throws ToDoListException{
        return toDoListRepository.findAllByUserId(userId);
    }

    /**
     * returns a list of all items from a ToDoList
     * @param listId - id of a toDoList
     * @throws ToDoListException
     */
    public List<ToDoItem> getToDoItemsOwned(long listId) throws ToDoListException{
        return toDoListRepository.findById(listId).get().getToDoItemList();
    }

    /**
     * adds a toDoItem to the database, listId
     * @param item that was posted
     * @return posted item
     * @throws ToDoListException if no ToDoList is found with the given ID
     */
    public ToDoItem postToDoItemToTheList(ToDoItem item) throws ToDoListException{
        if (!toDoListRepository.existsById(item.getToDoList().getId())) {
            throw new ToDoListException("No such ToDoList found");
        }
        return toDoItemRepository.save(item);
    }

    /**
     * updates toDoItem
     * @param item with updated data
     * @param id of an item to update
     * @return updated item
     * @throws ToDoListException if no ToDoList is found with the given ID
     */
    public ToDoItem updateToDoItemToTheList(ToDoItem item, long id) throws ToDoListException {
        Optional<ToDoItem> testToDoItem = toDoItemRepository.findById(id);
        if (testToDoItem.isEmpty())
            throw new ToDoListException("No such ToDoItem found");

        return testToDoItem.map(foundToDoItem -> {
            foundToDoItem.setValue(item.getValue());
            foundToDoItem.setStage(item.getStage());
            foundToDoItem.setUpdatedTimestamp(item.getUpdatedTimestamp());
            return toDoItemRepository.save(foundToDoItem);
        }).get();
    }
}
