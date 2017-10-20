package com.jira.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jira.cotract.IProjectService;
import com.jira.exceptions.ResourceNotFoundException;
import com.jira.model.Project;

@RestController
@RequestMapping("/project")
public class ProjectController {
	IProjectService projectService;

	@Autowired
	public ProjectController(IProjectService projectService) {
		this.projectService = projectService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Project> getProjectList(HttpServletRequest request) throws ResourceNotFoundException {
		return this.projectService.getProjectList(request);
	}
}
