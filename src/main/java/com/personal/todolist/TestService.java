package com.personal.todolist;

import com.personal.todolist.entity.ToDoItem;
import com.personal.todolist.entity.ToDoList;
import com.personal.todolist.entity.User;
import com.personal.todolist.exceptions.HelloWorldException;
import com.personal.todolist.repository.ToDoItemRepository;
import com.personal.todolist.repository.ToDoListRepository;
import com.personal.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    private UserRepository userRepository;
    private ToDoListRepository listRepository;
    private ToDoItemRepository itemRepository;

    @Autowired
    public TestService(UserRepository userRepository, ToDoListRepository listRepository, ToDoItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.listRepository = listRepository;
        this.itemRepository = itemRepository;
    }

    public String getUserNameToReturn() throws HelloWorldException {
//      getting entity list
        List<User> helloWorldList = userRepository.findAll();
//        checking the amount of entities and throwing appropriate exceptions
        if (helloWorldList.size() == 0) {
            throw new HelloWorldException("Could not find any entity");
        }
//      returning found value
        return helloWorldList.get(0).getName();
    }

    public int getListItemCountInList(long id) throws HelloWorldException{
        Optional<ToDoList> list = listRepository.findById(id);
        if (!list.isPresent())
            throw new HelloWorldException("list is empty");

        return list.get().getToDoItemList().size();
    }


}
