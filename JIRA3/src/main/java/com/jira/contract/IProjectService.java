package com.jira.contract;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.jira.exceptions.ResourceNotFoundException;
import com.jira.model.Project;

public interface IProjectService {

	Set<Project> getProjectList() throws ResourceNotFoundException;

	Project getProjectById(int id, HttpServletRequest request) throws ResourceNotFoundException;

	void saveProject(Project project,HttpServletRequest request);
}
