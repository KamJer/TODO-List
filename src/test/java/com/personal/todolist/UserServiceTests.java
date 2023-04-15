package com.personal.todolist;

import com.personal.todolist.entity.User;
import com.personal.todolist.exceptions.UserException;
import com.personal.todolist.repository.UserRepository;
import com.personal.todolist.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
class UserServiceTests {

    private long userId = 50;
    private User userKamil = new User(userId, "kamjer", "password", "Kamil");
    private User userKamilUpdated = new User(userId, "kamjer97", "password", "Kamil");

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    void insertUserTest() throws UserException {
        Mockito.when(userRepository.save(userKamil)).thenReturn(userKamil);
        Assertions.assertEquals(userService.insertUser(userKamil), userKamil);
    }
    @Test
    void getUserByIdTest() throws UserException {
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(userKamil));
        Assertions.assertEquals(userService.getUserById(userId), userKamil);
    }
    @Test
    void updateUserTest() throws UserException {
        Mockito.when(userRepository.save(userKamilUpdated)).thenReturn(userKamilUpdated);
        Assertions.assertEquals(userService.updateUser(userKamilUpdated), userKamilUpdated);
    }
    @Test
    void deleteUserByIdTest() throws UserException {
        userService.deleteUserById(userKamil.getId());
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(userId);
    }

    @Test
    public void updateUserUserExceptionLoginToLongTest() {
        User usetTest = new User(userId, "ThisLoginIsWayToLong", "password", "Kamil");
        Exception exception = Assertions.assertThrows(UserException.class, () -> {
            User userToTest = userService.updateUser(usetTest);
        });

        String expectedMessage = "Login can not be longer than 10 characters";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(expectedMessage.contains(actualMessage));
    }
}
