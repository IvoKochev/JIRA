package com.jira.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jira.contract.IProjectService;
import com.jira.contract.UserService;
import com.jira.exceptions.ResourceNotFoundException;
import com.jira.model.Project;
import com.jira.model.User;
import com.jira.repository.ProjectRepository;

@Service("projectService")
public class ProjectServiceImpl implements IProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private UserService userService;

	@Override
	public Set<Project> getProjectList() throws ResourceNotFoundException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		return user.getProjects();
	}

	@Override
	public Project getProjectById(int id) throws ResourceNotFoundException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		Project p = new Project();
		p.setId(id);
		if (user.getProjects().contains(p)) {
			return this.projectRepository.findById(id);
		}
		throw new ResourceNotFoundException();
	}

	@Override
	public void save(Project project, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		project.setImgurl("/images/project.jpg");
		project.setUsers(new HashSet<>(Arrays.asList(user)));
		project.setOwner(user);
		this.projectRepository.save(project);
	}
}
