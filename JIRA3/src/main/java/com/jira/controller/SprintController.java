/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jira.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jira.contract.ISprintService;
import com.jira.exceptions.ResourceNotFoundException;
import com.jira.exceptions.SprintException;
import com.jira.model.Issue;
import com.jira.model.Sprint;

/**
 *
 * @author ivo
 */
@Controller
public class SprintController {

	@Autowired
	private ISprintService sprintService;

	@GetMapping("/common/createSprint")
	public String getSprint(Model model) throws ResourceNotFoundException {
		Sprint sprint = new Sprint();
		model.addAttribute("sprint", sprint);
		return "common/createSprint";
	}

	@PostMapping("/createSprint")
	public String createSprint(Model model, @ModelAttribute("sprint") Sprint sprint, HttpServletRequest request)
			throws SprintException {
		int id = sprintService.saveSprint(sprint, request);
		return "redirect:/common/home#!/projectView/" + id;
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

}
