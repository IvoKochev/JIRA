package jira.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jira.contracts.IProjectService;
import jira.models.Project;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	IProjectService projectService;

	@Autowired
	public ProjectController(IProjectService projectService) {
		this.projectService = projectService;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public List<Project> getProjectList() {
		return this.projectService.getProjectList();
	}

}
