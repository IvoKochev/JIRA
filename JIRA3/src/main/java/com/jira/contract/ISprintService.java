package com.jira.contract;

import javax.servlet.http.HttpServletRequest;

import com.jira.model.Sprint;

public interface ISprintService {
	public Sprint findSprintByName(HttpServletRequest request,String sprintName);
	public boolean removeSprintByName(String sprintName);
}
