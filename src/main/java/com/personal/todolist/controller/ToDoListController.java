package com.personal.todolist.controller;

import com.personal.todolist.entity.ToDoList;
import com.personal.todolist.exceptions.ToDoListException;
import com.personal.todolist.service.ToDoListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/toDoList")
public class ToDoListController {

    private ToDoListService toDoListService;

    private Logger log = Logger.getLogger(ToDoListController.class.getName());

    public ToDoListController(ToDoListService service){
        this.toDoListService = service;
    }

    @PutMapping("/toDoList/update/{id}")
    public ToDoList updateToDoList(@RequestBody ToDoList toDoList, @PathVariable long id) throws ToDoListException {
        log.info("PUT /toDoList/update/" + id + " : " + toDoList.toString());
        return toDoListService.updateToDoList(toDoList, id);
    }
    /**
     * Adds new ToDoList to the database
     * @param toDoList - data to add
     * @throws ToDoListException
     */
    @PostMapping("/add")
    public ToDoList postToDoList(@RequestBody ToDoList toDoList) throws ToDoListException {
        log.info("POST /toDoList/add" + " : " + toDoList.toString());
        return toDoListService.postToDoList(toDoList);
    }

    @GetMapping("/read/{id}")
    public ToDoList getToDoListById(@PathVariable long id) throws ToDoListException {
        log.info("GET /toDoList/read/" + id);
        return toDoListService.getToDoListById(id);
    }

    @GetMapping("/read/user/{userId}")
    public List<ToDoList> getToDoListsWithUserId(@PathVariable long userId) throws ToDoListException {
        log.info("GET /toDoList/read/user/" + userId);
        return toDoListService.getToDoListsByUserId(userId);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteToDoListById(@PathVariable long id) {
        log.info("DELETE /toDoList/delete/" + id);
        toDoListService.deleteToDoList(id);
    }
}
