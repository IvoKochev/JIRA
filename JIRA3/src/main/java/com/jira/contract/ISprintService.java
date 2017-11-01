package com.jira.contract;

import javax.servlet.http.HttpServletRequest;

import com.jira.exceptions.ResourceNotFoundException;
import com.jira.exceptions.SprintException;
import com.jira.model.Sprint;

public interface ISprintService {
	Sprint findSprintById(int id);

	void removeSprintById(int id) throws ResourceNotFoundException;

	int saveSprint(Sprint sprint, HttpServletRequest request) throws SprintException;

}
