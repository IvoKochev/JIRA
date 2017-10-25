package com.jira.controller;

import java.util.Set;

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
//@ModelAttribute anotaciq v metoda za vzemane na gotov model ot html stranica
@RestController
public class ProjectController {
	IProjectService projectService;

	@Autowired
	public ProjectController(IProjectService projectService) {
		this.projectService = projectService;
	}

	@RequestMapping(value = "/common/projects", method = RequestMethod.GET)
	public ModelAndView adminProjects() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/common/projects");
		return modelAndView;
	}

	@RequestMapping(value = "/common/projectView", method = RequestMethod.GET)
	public ModelAndView commonProjectViewPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/common/projectView");
		return modelAndView;
	}

	@RequestMapping(value = "/common/404", method = RequestMethod.GET)
	public ModelAndView errorNotFound() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/common/404");
		return modelAndView;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Set<Project> getProjectList() throws ResourceNotFoundException {
		return this.projectService.getProjectList();
	}

	@RequestMapping(value = "/common/projectView/{id}", method = RequestMethod.GET)
	public Project getProjectById(@PathVariable(name = "id") int id, HttpServletRequest request)
			throws ResourceNotFoundException {
		return this.projectService.getProjectById(id, request);
	}
}
