package com.personal.todolist;

import com.personal.todolist.exceptions.HelloWorldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController{
    @Autowired
    private TestService testService;

    @GetMapping("/getHello")
    public String getHelloWorld () {
        try {
            return testService.getValueToReturn();
        } catch (HelloWorldException e) {
            System.out.println(e.toString());
        }
        return "";
    }
}
