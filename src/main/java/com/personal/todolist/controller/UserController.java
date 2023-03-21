package com.personal.todolist.controller;

import com.personal.todolist.entity.User;
import com.personal.todolist.exceptions.CustomException;
import com.personal.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService service;
    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

    @PutMapping("/user/update/{id}")
    public User updateUser(@RequestBody User user, @PathVariable long id) throws CustomException {
        return service.updateUser(user, id);
    }
    @PostMapping("/user/add")
    public User postUser(@RequestBody User user) throws CustomException {
        return service.postUser(user);
    }

    @GetMapping("/user/read/{id}")
    public User getUserById(@PathVariable long id) throws CustomException {
     return service.getUserById(id);
    }

    @DeleteMapping("/user/delete")
    public void deleteUserById(@PathVariable long id) {
        service.deleteUser(id);
    }
}
