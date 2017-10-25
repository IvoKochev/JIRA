package com.jira.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jira.contract.IProjectService;
import com.jira.exceptions.ResourceNotFoundException;
import com.jira.model.Project;

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

	@RequestMapping(value = "/admin/createProject", method = RequestMethod.GET)
	public ModelAndView createProject() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("project", new Project());
		modelAndView.setViewName("/admin/createProject");
		return modelAndView;
	}

	@RequestMapping(value = "/createProject", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid @ModelAttribute("pForm") Project project, BindingResult bindingResult,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("redirect:/common/home#!/admin/createProject");
		} else {
			this.projectService.saveProject(project, request);
			modelAndView.setViewName("redirect:/common/home");
		}
		return modelAndView;
	}

}
