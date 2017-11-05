package com.jira.contract;

import javax.servlet.http.HttpServletRequest;

import com.jira.exceptions.ResourceNotFoundException;
import com.jira.exceptions.SprintException;
import com.jira.model.Sprint;

public interface ISprintService {
	Sprint findSprintById(int id);

	void deleteSprint(int id);

	int saveSprint(Sprint sprint, HttpServletRequest request) throws SprintException, ResourceNotFoundException;

}
