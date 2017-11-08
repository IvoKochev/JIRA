package com.jira.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jira.contract.IProjectService;
import com.jira.contract.UserService;
import com.jira.error.AbstractError;
import com.jira.exceptions.AccessDeniedException;
import com.jira.exceptions.ResourceNotFoundException;
import com.jira.model.Project;
import com.jira.model.User;
import com.jira.repository.UserHasProjectRepository;

@Controller
@SessionAttributes("project")
public class ProjectController extends AbstractError {

	private IProjectService projectService;
	@Autowired
	private UserHasProjectRepository userHasProjectRepository;
	@Autowired
	private UserService userService;

	@Autowired
	public ProjectController(IProjectService projectService, UserService userService) {
		this.projectService = projectService;
	}

	@ModelAttribute("project")
	public Project getExampleForm() {
		return new Project();
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
	public Project getProjectById(@PathVariable(name = "id") int id) {
		return this.projectService.getProjectById(id);
	}

	@GetMapping("/createProject")
	public String createProject(Model model) {
		if (!model.containsAttribute("project")) {
			model.addAttribute("project", new Project());
		}
		return "/admin/createProject";
	}

	@PostMapping(value = "/createProject")
	public String createNewUser(@Valid @ModelAttribute("project") final Project project,
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
		int projectId = this.projectService.shareProject(request);
		return "redirect:/common/home#!/projectView/" + projectId;
	}

	@PostMapping("/sendMail")
	public String sendMail(Model model, HttpServletRequest request) {
		int id = this.projectService.sendMail(request);
		return "redirect:/common/home#!/projectView/" + id;
	}

	@PostMapping("/deleteProject")
	public String deleteProject(HttpServletRequest request) throws AccessDeniedException, ResourceNotFoundException {
		int id = (int) Integer.parseInt(request.getParameter("projectId"));
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		Project project = this.projectService.getProjectById(id);

		if (project.getOwner().getId() != user.getId()) {
			throw new AccessDeniedException();
		}
		this.userHasProjectRepository.deleteProject(id);
		this.projectService.deleteProject(id);
		return "redirect:/common/home#!";
	}

	@PostMapping("/removeUser")
	public String removeUser(HttpServletRequest request) throws ResourceNotFoundException, AccessDeniedException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		int projectId = Integer.parseInt(request.getParameter("projectId"));
		int currentUserId = (int) request.getSession().getAttribute("user_id");
		Project project = projectService.getProjectById(projectId);
		int projectOwnerId = project.getOwner().getId();
		if (currentUserId != projectOwnerId) {
			throw new AccessDeniedException();
		}
		if (projectOwnerId == userId) {
			return "redirect:/common/home#!/projectView/" + projectId;
		}
		userService.removeUsersIssues(userId, projectId);
		userHasProjectRepository.removeUserFromProject(userId, projectId);
		return "redirect:/common/home#!/projectView/" + projectId;
	}
	@GetMapping("/common/overview")
	public String overview() throws ResourceNotFoundException {
		return "/common/overview";
	}

	@GetMapping("/common/angularError")
	public String error() throws ResourceNotFoundException {
		return "/common/angularError";
	}

	@Override
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String resourceNotFound(Model model) {
		model.addAttribute("img", "/images/error/404.jpg");
		model.addAttribute("message", "Resource Not Found");
		model.addAttribute("errorStatus", "404 Not Found ");
		return "/common/error";
	}
}
