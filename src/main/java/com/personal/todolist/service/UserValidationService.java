package com.personal.todolist.service;

import com.personal.todolist.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserValidationService {

    public boolean validateInfo(User user) {
        System.out.println(user.getLogin() + " : " + user.getLogin().length());
        if (user.getLogin().length() >= 10) {
            return false;
        }
        return true;
    }
}
