package com.personal.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private String valueToReturn = "Hello World";

    public String getValueToReturn() {
        return valueToReturn;
    }
}
