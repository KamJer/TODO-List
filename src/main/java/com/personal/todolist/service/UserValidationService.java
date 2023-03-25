package com.personal.todolist.service;

import com.personal.todolist.entity.User;

public class UserValidationService {

    public boolean validateInfo(User user) {
        if (user.getLogin().length() >= 10) {
            return false;
        }
        return true;
    }
}
