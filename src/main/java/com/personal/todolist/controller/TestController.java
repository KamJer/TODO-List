package com.personal.todolist.controller;

import com.personal.todolist.TestService;
import com.personal.todolist.exceptions.HelloWorldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController{

    private TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/getHello")
    public String getHelloWorld () throws HelloWorldException {
        return testService.getUserNameToReturn();
    }
}
