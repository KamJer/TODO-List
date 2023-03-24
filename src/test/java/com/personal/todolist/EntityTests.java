package com.personal.todolist;

import com.personal.todolist.controller.ToDoListController;
import com.personal.todolist.controller.UserController;
import com.personal.todolist.entity.ToDoList;
import com.personal.todolist.entity.User;
import com.personal.todolist.exceptions.UserException;
import com.personal.todolist.exceptions.ToDoListException;
import com.personal.todolist.repository.ToDoListRepository;
import com.personal.todolist.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EntityTests {

    private User userCheck = new User("Kamil", "test", "Kamil" );
    private ToDoList listCheck = new ToDoList("Testowa nazwa");
    @Autowired
    UserRepository userRepository;

    @Autowired
    ToDoListRepository listRepository;

    @Autowired
    UserController userController;

    @Autowired
    ToDoListController listController;

    @Test
    @Order(1)
    void postUserTest() throws UserException {
        User userToTest = userController.postUser(userCheck);
        long userId = lastInsertedUserId(userRepository);
        Assertions.assertTrue(userRepository.existsById(userId));
    }

    @Test
    @Order(2)
    void getUserTest() throws UserException {
        long userId = lastInsertedUserId(userRepository);

        User userToTest = userController.getUserById(userId);
        Assertions.assertTrue(userToTest.equals(userCheck));
    }

    @Test
    @Order(3)
    void updateUserTest() throws UserException {
        long userId = lastInsertedUserId(userRepository);
        User userUpdated = new User(userId, "Kamila", "test", "Kamila");
        User userToTest = userController.updateUser(userUpdated, userId);
        Assertions.assertTrue(userToTest.equals(userUpdated));
    }

    @Test
    @Order(4)
    void deleteUserTest() throws UserException {
        long userId = lastInsertedUserId(userRepository);
        userController.deleteUserById(userId);
        Assertions.assertFalse(userRepository.existsById(userId));
    }

    @Test
    @Order(5)
    void postUserExceptionTest() throws UserException {
        Exception exception = Assertions.assertThrows(UserException.class, () -> {
            User userToTest = new User("MuchToLongLogin", "test", "Kamil" );
            userController.postUser(userToTest);
        });

        String expectedMessage = "Login can not be longer than 10 characters";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.equals(expectedMessage));
    }
    @Test
    @Order(6)
    void getUserExceptionTest() throws UserException {
        Exception exception = Assertions.assertThrows(UserException.class, () -> {
            User userToTest = userController.getUserById(0);
        });

        String expectedMessage = "No such user found";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    @Order(7)
    void postToDoListTest() throws ToDoListException {
        listController.postToDoList(listCheck);
        long listId = lastInsertedToDoListId(listRepository);
        Assertions.assertTrue(listRepository.existsById(listId));
    }

    @Test
    @Order(8)
    void getToDoItemListByIdTest() throws ToDoListException {
        long userId = lastInsertedToDoListId(listRepository);

        ToDoList toDoListToTest = listController.getToDoListById(userId);
        Assertions.assertTrue(toDoListToTest.equals(listCheck));
    }

    @Test
    @Order(9)
    void updateToDoListTest() throws ToDoListException {
        ToDoList listUpdated = new ToDoList("Testowy 2");
        long userId = lastInsertedToDoListId(listRepository);
        ToDoList toDoListToTest = listController.updateToDoList(listUpdated, userId);
        Assertions.assertTrue(toDoListToTest.equals(listUpdated));
    }

    @Test
    @Order(10)
    void deleteToDoListTest() throws ToDoListException {
        long userId = lastInsertedToDoListId(listRepository);
        listController.deleteToDoListById(userId);
        Assertions.assertFalse(listRepository.existsById(userId));
    }

    public static long lastInsertedUserId(JpaRepository userRepository) {
        List<User> testlist = userRepository.findAll();
        return testlist.get(userRepository.findAll().size() - 1).getId();
    }

    public static long lastInsertedToDoListId(JpaRepository userRepository) {
        List<ToDoList> testlist = userRepository.findAll();
        return testlist.get(userRepository.findAll().size() - 1).getId();
    }
}
