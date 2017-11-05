package com.jira.contract;

import com.jira.exceptions.ResourceNotFoundException;
import com.jira.model.User;

public interface UserService {
	public User findUserByEmail(String email);

	public void saveUser(User user);

	public User findById(int id);

	public void updateUserRating(int id, double rating, int votes);

	public void removeUsersIssues(int userId, int projectId) throws ResourceNotFoundException;
}
