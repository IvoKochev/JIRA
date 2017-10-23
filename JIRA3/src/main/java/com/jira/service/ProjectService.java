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
		List<Project> projects = projectRepository.findByOwnerid((int) request.getSession().getAttribute("user_id"));
		if (projects == null || projects.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		for (Project p : projects) {
			p.getUser().setPassword(null);
		}
		return projects;
	}

	@Override
	public Project getProjectById(int id) throws ResourceNotFoundException {
		Project p = projectRepository.findById(id);
		if (p == null) {
			throw new ResourceNotFoundException();
		}
		p.getUser().setPassword(null);
		return p;
	}
}
