package com.jira.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jira.cotract.IProjectService;
import com.jira.exceptions.ResourceNotFoundException;
import com.jira.model.Project;
import com.jira.repository.ProjectRepository;

@Service
public class ProjectService implements IProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public List<Project> getProjectList(HttpServletRequest request) throws ResourceNotFoundException {
		return projectRepository.findByuserid((int) request.getSession().getAttribute("user_id"));
	}
}
