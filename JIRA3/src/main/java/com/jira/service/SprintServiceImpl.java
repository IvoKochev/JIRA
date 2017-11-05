package com.jira.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jira.contract.IProjectService;
import com.jira.contract.ISprintService;
import com.jira.exceptions.ResourceNotFoundException;
import com.jira.exceptions.SprintException;
import com.jira.model.Project;
import com.jira.model.Sprint;
import com.jira.repository.SprintRepository;

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
	public void deleteSprint(int id) {
            sprintRepository.deleteIssue(id);
	}

	@Override
	public int saveSprint(Sprint sprint, HttpServletRequest request) throws SprintException, ResourceNotFoundException {
		int id = Integer.parseInt(request.getParameter("projectId"));
		Project project = projectService.getProjectById(id);
		int ownerId = (int) request.getSession().getAttribute("user_id");
		sprint.setProject(project);
		sprint.setOwner_id(ownerId);
		sprint.setImgurl("/images/sprint.jpg");
		this.sprintRepository.save(sprint);
		return id;
	}

}
