package com.jira.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jira.contract.IProjectService;
import com.jira.contract.ISprintService;
import com.jira.exceptions.ResourceNotFoundException;
import com.jira.exceptions.SprintException;
import com.jira.model.Project;
import com.jira.model.Sprint;
import com.jira.repository.SprintRepository;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Service("sprintService")
public class SprintServiceImpl implements ISprintService {
	@Autowired
	private SprintRepository sprintRepository;

	@Autowired
	private IProjectService projectService;

	@Override
	public Sprint findSprintById(int id) {
		return sprintRepository.findById(id);
	}

	@Override
	public void removeSprintById(int id) throws ResourceNotFoundException {
		if (sprintRepository.findById(id) == null) {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = null;
			Sprint sprint = null;
			session = sessionFactory.getCurrentSession();
			sprint = (Sprint) session.load(Sprint.class, id);
			session.delete(sprint);
			session.flush();
		} else {
			throw new ResourceNotFoundException("Sprint not found");
		}
	}

	@Override
	public int saveSprint(Sprint sprint, HttpServletRequest request) throws SprintException {
		int id = Integer.parseInt(request.getParameter("projectId"));
		Project project = projectService.getProjectById(id);
		int owner_id = (int) request.getSession().getAttribute("user_id");
		sprint.setProject(project);
		sprint.setOwner_id(owner_id);
		sprint.setImgurl("/images/sprint.jpg");
		this.sprintRepository.save(sprint);
		return id;
	}

}
