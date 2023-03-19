package com.personal.todolist.service;

import com.personal.todolist.entity.ToDoList;
import com.personal.todolist.exceptions.CustomException;
import com.personal.todolist.repository.ToDoItemRepository;
import com.personal.todolist.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestService {

    private ToDoListRepository listRepository;
    private ToDoItemRepository itemRepository;

    @Autowired
    public TestService(ToDoListRepository listRepository, ToDoItemRepository itemRepository) {
        this.listRepository = listRepository;
        this.itemRepository = itemRepository;
    }

    public int getListItemCountInList(long id) throws CustomException {
        Optional<ToDoList> list = listRepository.findById(id);
        if (!list.isPresent())
            throw new CustomException("list is empty");

        return list.get().getToDoItemList().size();
    }


    public ToDoListRepository getListRepository() {
        return listRepository;
    }

    public void setListRepository(ToDoListRepository listRepository) {
        this.listRepository = listRepository;
    }

    public ToDoItemRepository getItemRepository() {
        return itemRepository;
    }

    public void setItemRepository(ToDoItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
}
