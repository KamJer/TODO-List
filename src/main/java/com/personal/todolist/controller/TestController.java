package com.personal.todolist.controller;

import com.personal.todolist.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController{

    private TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }
}
