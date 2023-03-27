package com.personal.todolist;

import com.personal.todolist.controller.UserController;
import com.personal.todolist.entity.User;
import com.personal.todolist.exceptions.UserException;
import com.personal.todolist.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UserControllerTests {

    private long userId = 50;
    private User userKamil = new User(userId, "kamjer", "password", "Kamil");
    private User userKamilUpdated = new User(userId, "kamjer97", "password", "Kamil");

    @MockBean
    UserService userService;

    @Autowired
    UserController userController;

    @Test
    public void putUserTest() throws UserException {
        Mockito.when(userService.insertUser(userKamil)).thenReturn(userKamil);
        User userTest = userController.putUser(userKamil);
        Assertions.assertEquals(userTest, userKamil);
    }
    @Test
    public void getUserByIdTest() throws UserException {
        Mockito.when(userService.getUserById(userId)).thenReturn(userKamil);
        User userTest = userController.getUserById(userId);
        Assertions.assertEquals(userTest, userKamil);
    }
    @Test
    public void postUserTest() throws UserException {
        Mockito.when(userService.updateUser(userKamilUpdated)).thenReturn(userKamilUpdated);
        User userTest = userController.updateUser(userKamilUpdated);
        Assertions.assertEquals(userTest, userKamilUpdated);
    }
    @Test
    public void deleteUserByIdTest() throws UserException{
        userController.deleteUserById(userKamil.getId());
        Mockito.verify(userService, Mockito.times(1)).deleteUserById(userId);
    }
}
