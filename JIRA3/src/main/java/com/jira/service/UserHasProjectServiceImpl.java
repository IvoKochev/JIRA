package com.jira.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jira.contract.IUserHasProject;
import com.jira.model.UserHasProject;
import com.jira.repository.UserHasProjectRepository;

@Service("userHasProject")
public class UserHasProjectServiceImpl implements IUserHasProject {
	@Autowired
	private UserHasProjectRepository userHasProjectRepository;

	@Override
	public Integer findUserHasProject(int user_id, int project_id) {
		return this.userHasProjectRepository.findUserHasProject(user_id, project_id);
	}

	@Override
	public void save(UserHasProject userHasProject) {
		this.userHasProjectRepository.save(userHasProject);

	}

}
