package com.personal.todolist;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class TestService {

    private String helloWorld = "Hello World";

    public String getHelloWorld() {
        return helloWorld;
    }
}
