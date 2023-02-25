package com.personal.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController{
    private TestService testService;

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping("/getHello")
    public String getHelloWorld () {
        return testService.getHelloWorld();
    }
//    @RequestMapping("/getHello")
//    public String getHelloWorld () {
//        return "Hello World";
//    }
}
