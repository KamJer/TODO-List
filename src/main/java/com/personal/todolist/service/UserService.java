package com.personal.todolist.service;

import com.personal.todolist.entity.User;
import com.personal.todolist.exceptions.UserException;
import com.personal.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserValidationService userValidationService;

    @Autowired
    public UserService(UserRepository userRepository, UserValidationService userValidationService) {
        this.userRepository = userRepository;
        this.userValidationService = userValidationService;
    }

    /**
     * Adds new User to the database
     * @param user - user to add
     * @throws UserException
     */
    public User insertUser(User user) throws UserException {
        if (!userValidationService.validateInfo(user)) {
            throw new UserException("Login can not be longer than 10 characters");
        };
        return userRepository.save(user);
    }

    /**
     * Updates user entity in a database
     * @param user - user data
     * @throws UserException
     */
    public User updateUser(User user) throws UserException {
        if (!userValidationService.validateInfo(user)) {
            throw new UserException("Login can not be longer than 10 characters");
        }
        if (user.getId().equals(null)) {
            throw new UserException("Passed user does not have id");
        }
        try {
            return userRepository.save(user);
        } catch (Exception ex) {
            throw new UserException("Something went wrong, user was not inserted");
        }

    }

    /**
     * Finds user with its id number
     * @param id - id number of a user to find
     * @return user with specific id number
     * @throws UserException
     */
    public User getUserById(long id) throws UserException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserException("No such user found");

        return user.get();
    }

    /**
     * Deletes user from database
     * @param id - id of a user to delete
     */
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }
}
