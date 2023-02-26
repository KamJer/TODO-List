package com.personal.todolist;

import com.personal.todolist.entity.HelloWorldEntity;
import com.personal.todolist.exceptions.HelloWorldException;
import com.personal.todolist.repository.HelloWorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    private HelloWorldRepository repository;

    @Autowired
    public TestService(HelloWorldRepository repository) {
        this.repository = repository;
    }

    public String getValueToReturn() throws HelloWorldException {
//      getting entity list
        List<HelloWorldEntity> helloWorldList = repository.findAll();
//        checking the amount of entities and throwing appropriate exceptions
        if (helloWorldList.size() == 0) {
            throw new HelloWorldException("Could not find any entity");
        } else if (helloWorldList.size() > 1) {
            throw new HelloWorldException("More then one entity");
        }

//      returning found value
        return helloWorldList.get(0).getValue();
    }
}
