package jira.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jira.contracts.IUserService;
import jira.exceptions.InvalidUserException;
import jira.exceptions.ResourceNotFoundException;
import jira.models.User;

@RestController
@RequestMapping("/account")
public class UserController {
	IUserService userService;

	@Autowired
	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public User signIn(@RequestBody User user, HttpServletRequest request) throws ResourceNotFoundException {
		return this.userService.signIn(user.getEmail(), user.getPassword(), request);
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public User signUn(@RequestBody User user, HttpServletRequest request) throws InvalidUserException {
		return this.userService.signUp(user.getEmail(), user.getPassword(), request);
	}
}
