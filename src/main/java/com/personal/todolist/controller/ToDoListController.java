package com.personal.todolist.controller;

import com.personal.todolist.entity.ToDoList;
import com.personal.todolist.exceptions.ToDoListException;
import com.personal.todolist.service.ToDoListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
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
    @PostMapping("/toDoList/add")
    public ToDoList postToDoList(@RequestBody ToDoList toDoList) throws ToDoListException {
        log.info("POST /toDoList/add" + " : " + toDoList.toString());
        return toDoListService.postToDoList(toDoList);
    }

    @GetMapping("/toDoList/read/{id}")
    public ToDoList getToDoListById(@PathVariable long id) throws ToDoListException {
        log.info("GET /toDoList/read/" + id);
        return toDoListService.getToDoListById(id);
    }

    @DeleteMapping("/toDoList/delete/{id}")
    public void deleteToDoListById(@PathVariable long id) {
        log.info("DELETE /toDoList/delete/" + id);
        toDoListService.deleteToDoList(id);
    }

}
