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
    User userKamil = new User(userId, "kamjer", "password", "Kamil");
    User userKamilUpdated = new User(userId, "kamjer97", "password", "Kamil");

    @MockBean
    UserRepository userRepository;

//    @InjectMocks
    @Autowired
    UserService userService;

    @Test
    void saveUserTest() throws UserException {
        Mockito.when(userRepository.save(userKamil)).thenReturn(userKamil);
        Assertions.assertEquals(userService.insertUser(userKamil), userKamil);
    }
    @Test
    void getUserByIdTest() throws UserException {
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(userKamil));
        Assertions.assertEquals(userService.getUserById(userId), userKamil);
    }

    @Test
    void updateUser() throws UserException {
        Mockito.when(userRepository.save(userKamilUpdated)).thenReturn(userKamilUpdated);
        Assertions.assertEquals(userService.updateUser(userKamilUpdated), userKamilUpdated);
    }

    @Test
    void deleteUserTest() throws UserException {
        userService.deleteUser(userKamil.getId());
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(userId);
    }
}
