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
    private String valueToReturn = "Hello World";

    @Autowired
    public TestService(HelloWorldRepository repository) {
        this.repository = repository;
    }

    public String getValueToReturn() throws HelloWorldException {
        List helloWorldList = repository.findAll();
        System.out.println(repository.findAll().size());
        if (helloWorldList.size() != 1) {
            throw new HelloWorldException();
        }

        return ((HelloWorldEntity) helloWorldList.get(0)).getValue();
    }
}
