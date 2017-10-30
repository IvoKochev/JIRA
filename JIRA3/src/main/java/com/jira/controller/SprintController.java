/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jira.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jira.contract.IProjectService;
import com.jira.contract.ISprintService;
import com.jira.exceptions.ResourceNotFoundException;
import com.jira.exceptions.SprintException;
import com.jira.model.Project;
import com.jira.model.Sprint;

/**
 *
 * @author ivo
 */
@RestController
public class SprintController {

    @Autowired
    private ISprintService sprintService;
    @Autowired
    private IProjectService projectService;

	@RequestMapping(value = "/common/createSprint", method = RequestMethod.GET)
	public ModelAndView getSprint() throws ResourceNotFoundException {
		Sprint sprint = new Sprint();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("sprint", sprint);
		modelAndView.setViewName("common/createSprint");
		return modelAndView;
	}

	@RequestMapping(value = "/createSprint", method = RequestMethod.POST)
	public ModelAndView createSprint(@ModelAttribute Sprint sprint, HttpServletRequest request) throws SprintException {
		int id = Integer.parseInt(request.getParameter("projectId"));
		Project project = projectService.getProjectById(id);
		int owner_id = (int) request.getSession().getAttribute("user_id");
		sprint.setProject(project);
		sprint.setOwner_id(owner_id);
		ModelAndView modelAndView = new ModelAndView();
		sprintService.saveSprint(sprint);
		modelAndView.setViewName("redirect:/common/home#!/projectView/" + id);
		return modelAndView;
	}

}
