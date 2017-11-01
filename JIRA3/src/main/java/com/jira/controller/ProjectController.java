package com.jira.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jira.contract.IProjectService;
import com.jira.contract.UserService;
import com.jira.exceptions.ResourceNotFoundException;
import com.jira.mail.Mail;
import com.jira.model.Project;

@Controller
public class ProjectController {

	private IProjectService projectService;

	@Autowired
	public ProjectController(IProjectService projectService, UserService userService) {
		this.projectService = projectService;
	}

	@GetMapping("/common/projects")
	public String adminProjects(Model model) {
		return "/common/projects";
	}

	@GetMapping("/common/projectView")
	public String commonProjectViewPage(Model model) {
		return "/common/projectView";
	}

	@GetMapping("/common/404")
	public String errorNotFound(Model model) {
		return "common/404";
	}

	@GetMapping("/list")
	@ResponseBody
	public Set<Project> getProjectList() throws ResourceNotFoundException {
		return this.projectService.getProjectList();
	}

	@GetMapping("/common/projectView/{id}")
	@ResponseBody
	public Project getProjectById(@PathVariable(name = "id") int id) throws ResourceNotFoundException {
		return this.projectService.getProjectById(id);
	}

	@GetMapping("/admin/createProject")
	public String createProject(Model model) {
		if (!model.containsAttribute("project")) {
			model.addAttribute("project", new Project());
		}
		return "/admin/createProject";
	}

	@PostMapping("/createProject")
	public String createNewUser(Model model, @ModelAttribute("project") final @Valid Project project,
			final BindingResult bindingResult, RedirectAttributes attr, HttpSession session,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			attr.addFlashAttribute("org.springframework.validation.BindingResult.project", bindingResult);
			attr.addFlashAttribute("project", project);
			return "redirect:/common/home#!/createProject";
		}
		this.projectService.save(project, request);

		return "redirect:/common/home";
	}

	@PostMapping("/share/project")
	public String share(Model model, HttpServletRequest request) throws ResourceNotFoundException {
		int projectId = this.projectService.sendMeil(request);
		return "redirect:/common/home#!/projectView/" + projectId;
	}

	@PostMapping("/sendMail")
	public String sendMail(Model model, HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("projectId"));
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String body = request.getParameter("body");
		new Mail(email, subject, body);
		return "redirect:/common/home#!/projectView/" + id;
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleResourceNotFoundException() {
		return "User not found";
	}
}
