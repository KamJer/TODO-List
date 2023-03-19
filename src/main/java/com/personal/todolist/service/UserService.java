package com.personal.todolist.service;

import com.personal.todolist.entity.User;
import com.personal.todolist.exceptions.CustomException;
import com.personal.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Updates user entity in a database
     * @param user - user data
     * @param id - id number of a user to update
     * @throws CustomException
     */
    public User updateUser(User user, long id) throws CustomException {
       Optional<User> testUser = userRepository.findById(id);
        if (!testUser.isPresent())
            throw new CustomException("No such user found");

        return userRepository.findById(id).map(foundUser -> {
            foundUser.setName(user.getName());
            foundUser.setLogin(user.getLogin());
            foundUser.setPassword(user.getPassword());
            return userRepository.save(foundUser);
        }).get();
    }

    /**
     * Finds user with its id number
     * @param id - id number of a user to finde
     * @return user with specific id number
     * @throws CustomException
     */
    public User getUserById(long id) throws CustomException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent())
            throw new CustomException("No such user found");

        return user.get();
    }

    /**
     * Adds new User to the database
     * @param user - user to add
     * @throws CustomException
     */
    public User postUser(User user) throws CustomException {
        if (!User.validateUserData(user)) {
            throw new CustomException("Login can not be longer than 10 characters");
        };
        return userRepository.save(user);
    }

    /**
     * Deletes user from database
     * @param id - id of a user to delete
     */
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



}
