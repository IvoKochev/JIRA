package com.jira.contract;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.jira.exceptions.ResourceNotFoundException;
import com.jira.model.Project;

public interface IProjectService {

	Set<Project> getProjectList() throws ResourceNotFoundException;

	Project getProjectById(int id) throws ResourceNotFoundException;

	void save(Project project,HttpServletRequest request);
}
