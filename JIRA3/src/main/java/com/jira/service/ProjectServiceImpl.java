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
import com.jira.mail.Mail;
import com.jira.model.Project;
import com.jira.model.User;
import com.jira.model.UserHasProject;
import com.jira.model.UserHasProjectId;
import com.jira.repository.ProjectRepository;
import com.jira.repository.UserHasProjectRepository;

@Service("projectService")
public class ProjectServiceImpl implements IProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private UserService userService;

	@Autowired
	private UserHasProjectRepository userHasProjectRepository;

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

	@Override
	public int sendMeil(HttpServletRequest request) {
		int projectId = Integer.parseInt(request.getParameter("projectId"));
		String email = request.getParameter("email");
		User user = this.userService.findUserByEmail(email);
		if (user == null) {
			throw new ResourceNotFoundException("Invalid user");
		}
		UserHasProject uhp = new UserHasProject();
		UserHasProjectId uhpi = new UserHasProjectId();
		uhpi.setUser_id(user.getId());
		uhpi.setProject_id(projectId);
		uhp.setUserHasProjectID(uhpi);
		userHasProjectRepository.save(uhp);
		new Mail(email, "JIRA", "You were added to a new project");
		
		return projectId;

	}
}
