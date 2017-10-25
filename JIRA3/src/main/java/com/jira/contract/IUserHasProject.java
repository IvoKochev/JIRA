package com.jira.contract;

import com.jira.model.UserHasProject;

public interface IUserHasProject {
	public Integer findUserHasProject(int user_id, int project_id);

	public void save(UserHasProject userHasProject);
}
