package com.jira.service;

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
			User u = this.userService.findById(project.getOwnerid());
			u.setPassword(null);
			project.setOwner(u);
		}
		return user.getProjects();
	}

	@Override
	public Project getProjectById(int id, HttpServletRequest request) throws ResourceNotFoundException {
//		if (this.iuserHasProject.findUserHasProject((int) request.getSession().getAttribute("user_id"), id) == 1) {
//			Project p = this.projectRepository.findById(id);
//			User owner = this.userService.findById(p.getOwnerid());
//			if (p.getOwnerid() == owner.getId()) {
//				User u = new User();
//				u.setName(owner.getName());
//				u.setEmail(owner.getEmail());
//				u.setId(owner.getId());
//				u.setImgurl(owner.getImgurl());
//				u.setRoles(owner.getRoles());
//				p.setOwner(u);
//				return p;
//			}
//			p.setOwner(owner);
//			return p;
//		}

		throw new ResourceNotFoundException();
	}

	@Override
	public void saveProject(Project project, HttpServletRequest request) {
		int userId = (int) request.getSession().getAttribute("user_id");
		project.setOwnerid(userId);
		project.setImgurl("/images/project.jpg");
		this.projectRepository.save(project);
		
	}
}
