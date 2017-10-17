package jira.contracts;

import java.security.NoSuchAlgorithmException;

import jira.exceptions.EmailException;
import jira.exceptions.UserException;
import jira.models.User;

public interface IUserService {

	User signIn(String email, String password) throws UserException, NoSuchAlgorithmException;

	User singUp(String email, String password) throws EmailException, NoSuchAlgorithmException;

}
