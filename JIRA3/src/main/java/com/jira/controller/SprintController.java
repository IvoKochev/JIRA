/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jira.controller;

<<<<<<< HEAD

import com.jira.contract.IProjectService;
import com.jira.contract.ISprintService;
import com.jira.exceptions.ResourceNotFoundException;
import com.jira.exceptions.SprintException;
import com.jira.model.Project;
import com.jira.model.Sprint;
import com.jira.repository.SprintRepository;
=======
>>>>>>> d0365d19fb05dc142826002acbf77297a014bbf2
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.ModelAttribute;

=======
import org.springframework.web.bind.annotation.ModelAttribute;
>>>>>>> d0365d19fb05dc142826002acbf77297a014bbf2
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

<<<<<<< HEAD


=======
import com.jira.contract.IProjectService;
import com.jira.contract.ISprintService;
>>>>>>> d0365d19fb05dc142826002acbf77297a014bbf2
import com.jira.exceptions.ResourceNotFoundException;
import com.jira.exceptions.SprintException;
import com.jira.model.Project;
import com.jira.model.Sprint;

<<<<<<< HEAD

=======
>>>>>>> d0365d19fb05dc142826002acbf77297a014bbf2
/**
 *
 * @author ivo
 */
@RestController
public class SprintController {

<<<<<<< HEAD
    @Autowired
    private SprintRepository sprintRepository;
    @Autowired
    private ISprintService sprintService;
    @Autowired
    private IProjectService projectService;
    
    @RequestMapping(value = "/common/createSprint", method = RequestMethod.GET)
    public ModelAndView getSprint(HttpServletRequest request) throws ResourceNotFoundException{
//        System.out.println("get controller");
        //int owner_id = (int) request.getSession().getAttribute("user_id");
        Sprint sprint = new Sprint();
        int id = Integer.parseInt(request.getParameter("projectId"));
//        sprint.setOwner_id(owner_id);
        sprint.setOwner_id(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sprint", sprint);
        modelAndView.addObject("projectId", id);
        modelAndView.setViewName("common/createSprint");
        
        return modelAndView;
    }
    
    @RequestMapping(value = "/common/createSprint", method = RequestMethod.POST)
    public ModelAndView createSprint(@ModelAttribute Sprint sprint, HttpServletRequest request) throws SprintException {        
        int id = Integer.parseInt(request.getParameter("projectId"));
        System.out.println("project id: " + id);
        //String date = request.getParameter("start_date");
        //System.out.println(date);
        Project project = projectService.getProjectById(id);
        int owner_id = (int) request.getSession().getAttribute("user_id");
        sprint.setProject_id(project);
        sprint.setOwner_id(owner_id);
        //sprint.setStart_date(new java.sql.Timestamp(new java.util.Date().getTime()));
        ModelAndView modelAndView = new ModelAndView();
        sprintService.saveSprint(sprint);
        modelAndView.setViewName("redirect:/common/projectView");
        return modelAndView;
    }


=======
	@Autowired
	private ISprintService sprintService;
	@Autowired
	private IProjectService projectService;

	@RequestMapping(value = "/common/createSprint", method = RequestMethod.GET)
	public ModelAndView getSprint(HttpServletRequest request) throws ResourceNotFoundException {
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
		sprint.setProject_id(project);
		sprint.setOwner_id(owner_id);
		ModelAndView modelAndView = new ModelAndView();
		sprintService.saveSprint(sprint);
		modelAndView.setViewName("redirect:/common/home#!/projectView/"+ id);
		return modelAndView;
	}
>>>>>>> d0365d19fb05dc142826002acbf77297a014bbf2
}
