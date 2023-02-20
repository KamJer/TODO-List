package com.personal.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController{
    @Autowired
    private TestService testService;

    @GetMapping("/getHello")
    public String getHelloWorld () {
        return testService.getValueToReturn();
    }
}
