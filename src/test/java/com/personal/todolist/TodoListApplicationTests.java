package com.personal.todolist;

import com.personal.todolist.controller.UserController;
import com.personal.todolist.entity.User;
import com.personal.todolist.exceptions.CustomException;
import com.personal.todolist.repository.UserRepository;
import com.personal.todolist.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.jupiter.api.*;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TodoListApplicationTests {

	private User userCheck = new User("Kamil", "test", "Kamil" );
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserController userController;

	@Test
	@Order(1)
	void postUserTest() throws CustomException {
		User userToTest = userController.postUser(userCheck);
		long userId = lastInsertedEntityId(userRepository);
		Assertions.assertTrue(userRepository.existsById(userId));
	}

	@Test
	@Order(2)
	void getUserTest() throws CustomException {
		long userId = lastInsertedEntityId(userRepository);

		User userToTest = userController.getUserById(userId);
		Assertions.assertTrue(userToTest.isUserTheSame(userCheck));
	}

	@Test
	@Order(3)
	void updateUserTest() throws  CustomException {
		User userUpdated = new User("Kamila", "test", "Kamila");
		long userId = lastInsertedEntityId(userRepository);
		User userToTest = userController.updateUser(userUpdated, userId);
		Assertions.assertTrue(userToTest.isUserTheSame(userUpdated));
	}

	@Test
	@Order(4)
	void deleteUserTest() throws CustomException {
		long userId = lastInsertedEntityId(userRepository);
		userController.deleteUserById(userId);
		Assertions.assertFalse(userRepository.existsById(userId));
	}

	@Test
	@Order(5)
	void postUserExceptionTest() throws CustomException{
		Exception exception = Assertions.assertThrows(CustomException.class, () -> {
			User userToTest = new User("MuchToLongLogin", "test", "Kamil" );
			userController.postUser(userToTest);
		});

		String expectedMessage = "Login can not be longer than 10 characters";
		String actualMessage = exception.getMessage();
		Assertions.assertTrue(actualMessage.equals(expectedMessage));
	}
	@Test
	@Order(6)
	void getUserExceptionTest() throws CustomException{
		Exception exception = Assertions.assertThrows(CustomException.class, () -> {
			User userToTest = userController.getUserById(0);
		});

		String expectedMessage = "No such user found";
		String actualMessage = exception.getMessage();
		Assertions.assertTrue(actualMessage.equals(expectedMessage));
	}
	public static long lastInsertedEntityId(UserRepository userRepository) {
		List<User> testlist = userRepository.findAll();
		return testlist.get(testlist.size() - 1).getId();
	}
}
