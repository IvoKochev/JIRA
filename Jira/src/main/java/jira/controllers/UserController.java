package jira.controllers;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jira.contract.IUserService;
import jira.exceptions.EmailException;
import jira.exceptions.UserException;
import jira.models.User;

@RestController
@RequestMapping("/user")
public class UserController {
	IUserService userService;

	@Autowired
	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public User signIn(@RequestBody User user) throws UserException, NoSuchAlgorithmException {
		return this.userService.signIn(user.getEmail(), user.getPassword());
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public User signUn(@RequestBody User user) throws EmailException, NoSuchAlgorithmException {
		return this.userService.singUp(user.getEmail(), user.getPassword());
	}
}
