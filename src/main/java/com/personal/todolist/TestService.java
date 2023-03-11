package com.personal.todolist;

import com.personal.todolist.entity.User;
import com.personal.todolist.exceptions.HelloWorldException;
import com.personal.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    private UserRepository repository;

    @Autowired
    public TestService(UserRepository repository) {
        this.repository = repository;
    }

    public String getUserNameToReturn() throws HelloWorldException {
//      getting entity list
        List<User> helloWorldList = repository.findAll();
//        checking the amount of entities and throwing appropriate exceptions
        if (helloWorldList.size() == 0) {
            throw new HelloWorldException("Could not find any entity");
        }

//      returning found value
        return helloWorldList.get(0).getName();
    }
}
