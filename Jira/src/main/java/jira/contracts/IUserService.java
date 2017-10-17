package jira.contracts;

import java.security.NoSuchAlgorithmException;

import jira.exceptions.InvalidUserException;
import jira.models.User;

public interface IUserService {

	User signIn(String email, String password) throws InvalidUserException, NoSuchAlgorithmException;

	User singUp(String email, String password) throws InvalidUserException, NoSuchAlgorithmException;

}
