package com.personal.todolist.controller;

import com.personal.todolist.entity.User;
import com.personal.todolist.exceptions.UserException;
import com.personal.todolist.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    private Logger log = Logger.getLogger(UserController.class.getName());


    public UserController(UserService service){
        this.userService = service;
    }

    @PostMapping("/update/{id}")
    public User updateUser(@RequestBody User user) throws UserException {
        log.info("POST /user/update/" + user.getId() + " : " + user);
        return userService.updateUser(user);
    }
    @PutMapping("/add")
    public User putUser(@RequestBody User user) throws UserException {
        log.info("PUT /user/add" + " : " + user);
        return userService.insertUser(user);
    }

    @GetMapping("/read/{id}")
    public User getUserById(@PathVariable long id) throws UserException {
        log.info("GET /user/read/" + id);
        return userService.getUserById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable long id) {
        log.info("DELETE /user/delete/" + id);
        userService.deleteUserById(id);
    }
}
