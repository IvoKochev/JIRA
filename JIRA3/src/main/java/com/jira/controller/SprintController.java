/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jira.controller;

import com.jira.contract.IProjectService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jira.contract.ISprintService;
import com.jira.error.AbstractError;
import com.jira.exceptions.AccessDeniedException;
import com.jira.exceptions.ResourceNotFoundException;
import com.jira.exceptions.SprintException;
import com.jira.model.Issue;
import com.jira.model.Sprint;

@Controller
public class SprintController extends AbstractError {

	private ISprintService sprintService;
        @Autowired
        private IProjectService projectService;

	@Autowired
	public SprintController(ISprintService sprintService) {
		this.sprintService = sprintService;
	}

	@GetMapping("/common/createSprint")
	public String getSprint(Model model) throws ResourceNotFoundException {
		Sprint sprint = new Sprint();
		model.addAttribute("sprint", sprint);
		return "common/createSprint";
	}

	@PostMapping("/createSprint")
	public String createSprint(Model model, @Valid @ModelAttribute("sprint") Sprint sprint, BindingResult bindingResult,
			HttpServletRequest request) throws SprintException, ResourceNotFoundException {
		int projectId = Integer.parseInt(request.getParameter("projectId"));
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		if (endDate == null || endDate.isEmpty() || startDate == null || startDate.isEmpty()) {
			return "redirect:/common/home#!/createSprint/" + projectId;
		}
		LocalDate actualStartDate = LocalDate.parse(startDate, dtf);
		LocalDate actualEndDate = LocalDate.parse(endDate, dtf);
                if(actualStartDate.isAfter(actualEndDate)) {
                    return "redirect:/common/home#!/createSprint/" + projectId;
                }
		sprint.setStart_date(java.sql.Date.valueOf(actualStartDate));
		sprint.setEnd_date(java.sql.Date.valueOf(actualEndDate));
		if (bindingResult.hasErrors()) {
			return "redirect:/common/home#!/createSprint/" + projectId;
		}
		sprintService.saveSprint(sprint, request);
		return "redirect:/common/home#!/projectView/" + projectId;
	}

	@GetMapping("/common/sprintView")
	public String getIssueView() {
		return "common/sprintView";
	}

	@GetMapping("/common/sprintView/{id}")
	@ResponseBody
	public Set<Issue> getIssueViewPost(@PathVariable(value = "id") int id) {
		Sprint sprint = this.sprintService.findSprintById(id);
		return sprint.getIssues();
	}

	@PostMapping("/deleteSprint")
	public String deleteSprint(HttpServletRequest request) throws AccessDeniedException {
		int userId = (int) request.getSession().getAttribute("user_id");
		int sprintId = Integer.parseInt(request.getParameter("sprintId"));
		Sprint sprint = sprintService.findSprintById(sprintId);
		int projectId = sprint.getProject().getId();
                int projectOwnerId = projectService.getProjectById(projectId).getOwner().getId();
                System.out.println("user id: " + userId);
                System.out.println("owner id: " + sprint.getOwner_id());
                System.out.println("project owner id: " + projectOwnerId);
		if (userId != sprint.getOwner_id() && userId != projectOwnerId) {
			throw new AccessDeniedException();
		}
		sprintService.deleteSprint(sprintId);
		return "redirect:/common/home#!/projectView/" + projectId;
	}
}
