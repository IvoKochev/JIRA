package com.jira.cotract;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jira.exceptions.ResourceNotFoundException;
import com.jira.model.Project;

public interface IProjectService {

	List<Project> getProjectList(HttpServletRequest request) throws ResourceNotFoundException;

	Project getProjectById(int id) throws ResourceNotFoundException;
}
