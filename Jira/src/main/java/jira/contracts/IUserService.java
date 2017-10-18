package jira.contracts;


import javax.servlet.http.HttpServletRequest;

import jira.exceptions.InvalidUserException;
import jira.exceptions.ResourceNotFoundException;
import jira.models.User;

public interface IUserService {

	User signIn(String email, String password,HttpServletRequest request) throws ResourceNotFoundException;

	User singUp(String email, String password,HttpServletRequest request) throws InvalidUserException;
}
