package com.jira.service;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jira.cotract.IProjectService;
import com.jira.cotract.UserService;
import com.jira.exceptions.ResourceNotFoundException;
import com.jira.model.Project;
import com.jira.model.User;
import com.jira.repository.ProjectRepository;
import com.jira.repository.UserHasProjectRepository;
import com.jira.repository.UserRepository;

@Service
public class ProjectService implements IProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private UserHasProjectRepository userHasProjectRepository;

	@Override
	public Set<Project> getProjectList() throws ResourceNotFoundException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		Set<Project> projects = user.getProjects();
		Iterator<Project> pIter = projects.iterator();
		while (pIter.hasNext()) {
			Project project = (Project) pIter.next();
			if (project.getOwnerid() == user.getId()) {
				User u = new User();
				u.setName(user.getName());
				u.setEmail(user.getEmail());
				u.setId(user.getId());
				u.setImgurl(user.getImgurl());
				u.setRoles(user.getRoles());
				project.setOwner(u);
				continue;
			}
			User u = userRepository.findByid(project.getOwnerid());
			u.setProjects(null);
			u.setPassword(null);
			project.setOwner(u);
		}
		return user.getProjects();
	}

	@Override
	public Project getProjectById(int id, HttpServletRequest request) throws ResourceNotFoundException {
		if (this.userHasProjectRepository.findUserHasProject((int) request.getSession().getAttribute("user_id"),
				id) == 1) {
			return this.projectRepository.findById(id);
		}

		throw new ResourceNotFoundException();
	}
}
