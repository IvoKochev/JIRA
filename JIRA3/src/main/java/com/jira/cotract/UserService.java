package com.jira.cotract;

import com.jira.model.User;

public interface UserService {
	public User findUserByEmail(String email);

	public void saveUser(User user);

	public User findById(int id);

}
