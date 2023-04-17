package com.personal.todolist;

import com.personal.todolist.controller.ToDoListController;
import com.personal.todolist.controller.UserController;
import com.personal.todolist.entity.ToDoItem;
import com.personal.todolist.entity.ToDoList;
import com.personal.todolist.entity.User;
import com.personal.todolist.exceptions.ToDoListException;
import com.personal.todolist.exceptions.UserException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ToDoListControllerTest {

    @Autowired
    ToDoListController toDoListController;

    @Autowired
    UserController userController;

    @Autowired
    Environment env;

    @Test
    @Order(1)
    public void addToDoListToDataBaseThrowToDoListException() {
        ToDoList toDoList = new ToDoList(1, 1, "test");
//        ToDoItem itemToAdd = new ToDoItem(1, "test", Stage.IN_PROGRESS, toDoList);
        Exception exception = Assertions.assertThrows(UserException.class, () -> {
            toDoListController.postToDoList(toDoList);
        });
        String expectedMessage = "No such User found";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.equals(expectedMessage));
    }
    @Test
    @Order(2)
    public void addUserToDataBase() throws UserException {
        User user = new User("LoginTest", "password", "NameTest");
        User userIfPuttedIn = userController.putUser(user);
        Assertions.assertTrue(userIfPuttedIn != null);
    }
    @Test
    @Order(3)
    public void addToDoItemToDataBaseThrowToDoListException() {
        ToDoList toDoList = new ToDoList(1, 1, "test");
        ToDoItem itemToAdd = new ToDoItem(1, "test", Stage.IN_PROGRESS, toDoList);
        Exception exception = Assertions.assertThrows(ToDoListException.class, () -> {
            toDoListController.postToDoItemToDoList(itemToAdd);
        });
        String expectedMessage = "No such ToDoList found";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.equals(expectedMessage));
    }
}
