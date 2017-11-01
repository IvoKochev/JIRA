package com.jira.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jira.contract.IProjectService;
import com.jira.contract.UserService;
import com.jira.exceptions.ResourceNotFoundException;
import com.jira.mail.Mail;
import com.jira.model.Project;
import com.jira.model.User;
import com.jira.model.UserHasProject;
import com.jira.model.UserHasProjectId;
import com.jira.repository.UserHasProjectRepository;

@Controller
public class ProjectController {

	private IProjectService projectService;

	private UserService userService;

	@Autowired
	private UserHasProjectRepository userHasProjectRepository;

	@Autowired
	public ProjectController(IProjectService projectService, UserService userService) {
		this.projectService = projectService;
		this.userService = userService;
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
		modelAndView.addObject("message", "");
		modelAndView.setViewName("/common/projectView");
		return modelAndView;
	}

	@RequestMapping(value = "/common/404", method = RequestMethod.GET)
	public ModelAndView errorNotFound() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("common/404");
		return modelAndView;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Set<Project> getProjectList() throws ResourceNotFoundException {
		return this.projectService.getProjectList();
	}

	@RequestMapping(value = "/common/projectView/{id}", method = RequestMethod.GET)
	public Project getProjectById(@PathVariable(name = "id") int id) throws ResourceNotFoundException {
		return this.projectService.getProjectById(id);
	}

	@RequestMapping(value = "/admin/createProject", method = RequestMethod.GET)
	public String createProject(Model model) {
		if (!model.containsAttribute("project")) {
			System.err.println("KOZAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			model.addAttribute("project", new Project());
		}
		return "/admin/createProject";
	}

	@RequestMapping(value = "/createProject", method = RequestMethod.POST)
	public String createNewUser(Model model, @Valid Project project, BindingResult bindingResult,
			RedirectAttributes attr, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			bindingResult.rejectValue("name", "kurec");
			attr.addFlashAttribute("org.springframework.validation.BindingResult.project", bindingResult);
			attr.addFlashAttribute("project", project);
			return "redirect:" + request.getContextPath() + "/common/home#!/createProject";
		}
		this.projectService.save(project, request);

		return "redirect:/common/home";
	}

	@RequestMapping(value = "/share/project", method = RequestMethod.POST)
	public ModelAndView share(HttpServletRequest request) throws ResourceNotFoundException {
		ModelAndView modelAndView = new ModelAndView();
		int projectId = Integer.parseInt(request.getParameter("projectId"));
		String email = request.getParameter("email");
		User user = this.userService.findUserByEmail(email);
		if (user == null) {
			throw new ResourceNotFoundException("Invalid user");
		}
		UserHasProject uhp = new UserHasProject();
		UserHasProjectId uhpi = new UserHasProjectId();
		uhpi.setUser_id(user.getId());
		uhpi.setProject_id(projectId);
		uhp.setUserHasProjectID(uhpi);
		userHasProjectRepository.save(uhp);
		new Mail(email, "JIRA", "You were added to a new project");
		modelAndView.setViewName("redirect:/common/home#!/projectView/" + projectId);
		return modelAndView;
	}

	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public ModelAndView sendMail(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		int id = Integer.parseInt(request.getParameter("projectId"));
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String body = request.getParameter("body");
		new Mail(email, subject, body);
		modelAndView.setViewName("redirect:/common/home#!/projectView/" + id);
		return modelAndView;
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleResourceNotFoundException() {
		return "User not found";
	}
}
