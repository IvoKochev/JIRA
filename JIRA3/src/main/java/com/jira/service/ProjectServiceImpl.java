package com.jira.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
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
import com.jira.model.Sprint;
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
		Project project = new Project();
		project.setId(id);
		if (!user.getProjects().contains(project)) {
			throw new ResourceNotFoundException();
		}
		project = this.projectRepository.findById(id);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // Or whatever format fits best your needs.
		Iterator<Sprint> sprintIter = project.getSprints().iterator();
		while (sprintIter.hasNext()) {
			Sprint sprint = (Sprint) sprintIter.next();
			sprint.setEndDate(sdf.format(sprint.getEnd_date()));
			sprint.setStartDate(sdf.format(sprint.getStart_date()));
		}

		return project;
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
	public int shareProject(HttpServletRequest request) throws ResourceNotFoundException {
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

	@Override
	public void deleteProject(int id) {
		this.projectRepository.deleteProject(id);
	}

	public void delete(int id) {
		this.projectRepository.delete(id);
	}

	@Override
	public int sendMail(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("projectId"));
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String body = request.getParameter("body");
		new Mail(email, subject, body);
		return id;
	}
}
