package com.jira.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jira.cotract.IProjectService;
import com.jira.exceptions.ResourceNotFoundException;
import com.jira.model.Project;

@RestController
public class ProjectController {
	IProjectService projectService;

	@Autowired
	public ProjectController(IProjectService projectService) {
		this.projectService = projectService;
	}

	@RequestMapping(value = "/admin/projects", method = RequestMethod.GET)
	public ModelAndView adminProjects() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/admin/projects");
		return modelAndView;
	}

	@RequestMapping(value = "/admin/projectView", method = RequestMethod.GET)
	public ModelAndView admin() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/admin/projectView");
		return modelAndView;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Project> getProjectList(HttpServletRequest request) throws ResourceNotFoundException {
		return this.projectService.getProjectList(request);
	}

	@RequestMapping(value = "/admin/projectView/{id}", method = RequestMethod.GET)
	public Project getProjectById(@PathVariable(name = "id") int id, HttpServletRequest request)
			throws ResourceNotFoundException {
		return this.projectService.getProjectById(id);
	}
}
