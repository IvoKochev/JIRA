package com.jira.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jira.contract.IAttachmentService;
import com.jira.contract.IIssueService;
import com.jira.contract.IProjectService;
import com.jira.contract.ISprintService;
import com.jira.contract.UserService;
import com.jira.error.AbstractError;
import com.jira.exceptions.AccessDeniedException;
import com.jira.exceptions.ResourceNotFoundException;
import com.jira.mail.Mail;
import com.jira.model.Attachment;
import com.jira.model.Issue;
import com.jira.model.Project;
import com.jira.model.Sprint;
import com.jira.model.User;
import com.jira.repository.IssueRepository;
import com.jira.repository.UserRepository;

@Controller
public class IssueController extends AbstractError{
	@Autowired
	private IProjectService projectService;
	@Autowired
	private IIssueService issueService;
	@Autowired
	private ISprintService sprintService;
	@Autowired
	private IssueRepository issueRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private IAttachmentService attachmentService;

	@GetMapping("/common/createIssue")
	public String issueCreateTemplate(Model model) {
		model.addAttribute("issue", new Issue());
		return "common/createIssue";
	}

	@PostMapping("/createIssue")
	public String createIssue(@Valid @ModelAttribute Issue issue, BindingResult bindingResult,
			HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("sprintId"));
		if (bindingResult.hasErrors()) {
			return "redirect:/common/home#!/createIssue/" + id;
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		issue.setAsignee(user);
		issue.setReporter(user);
		Sprint sprint = sprintService.findSprintById(id);
		issue.setSprint(sprint);
		issue.setStatus("TO DO");
		issueService.saveIssue(issue);
		return "redirect:/common/home#!/sprintView/" + id;
	}

	@GetMapping("/common/issueView")
	public ModelAndView getIssueViewTemplate() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("common/issueView");
		return modelAndView;
	}

	@RequestMapping(value = "/common/issueView/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Issue getIssue(@PathVariable(value = "id") int id) {
		Issue issue = issueRepository.findById(id);
		return issue;
	}

	@PostMapping("/setAsignee")
	public ModelAndView setAsignee(HttpServletRequest request) throws ResourceNotFoundException {
		ModelAndView modelAndView = new ModelAndView();
		int userId = (int) request.getSession().getAttribute("user_id");
		int issueId = Integer.parseInt(request.getParameter("issueId"));
		Issue issue = issueService.getIssue(issueId);
		if (userId != issue.getReporter().getId()) {
			modelAndView.setViewName("redirect:/common/home#!/issueView/" + issueId);
			return modelAndView;
		}

		if (userId != issue.getReporter().getId()) {
			modelAndView.setViewName("redirect:/common/home#!/issueView/" + issueId);
			return modelAndView;
		}
		String email = request.getParameter("asignee");
		if (email == null || email.isEmpty()) {
			modelAndView.setViewName("redirect:/common/home#!/issueView/" + issueId);
			return modelAndView;
		}
		if (!issueService.isValidEmailAddress(email)) {
			System.out.println("nepravilno");
			modelAndView.setViewName("redirect:/common/home#!/issueView/" + issueId);
			return modelAndView;
		}
		System.out.println("pravilno");
		Sprint sprint = sprintService.findSprintById(issue.getSprint().getId());
		Project project = projectService.getProjectById(sprint.getProject().getId());
		User user = userRepository.findByEmail(email);
		if (user != null && user.getProjects().contains(project)) {
			issue.setAsignee(user);
			issueService.saveIssue(issue);
		}
		modelAndView.setViewName("redirect:/common/home#!/issueView/" + issueId);
		return modelAndView;
	}

	@PostMapping("/setStatus")
	public ModelAndView setStatus(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		int issueId = Integer.parseInt(request.getParameter("issueId"));
		int userId = (int) request.getSession().getAttribute("user_id");
		Issue issue = issueService.getIssue(issueId);
		System.out.println("user id: " + userId + " reporter: " + issue.getReporter().getId() + " asignee: "
				+ issue.getAsignee().getId());
		if (userId != issue.getAsignee().getId() && userId != issue.getReporter().getId()) {
			modelAndView.setViewName("redirect:/common/home#!/issueView/" + issueId);
			return modelAndView;
		}
		String status = request.getParameter("status");
		issue.setStatus(status);
		issueService.saveIssue(issue);
		modelAndView.setViewName("redirect:/common/home#!/issueView/" + issueId);
		return modelAndView;
	}

	@PostMapping("/setPriority")
	public ModelAndView setPriority(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		int userId = (int) request.getSession().getAttribute("user_id");
		int issueId = Integer.parseInt(request.getParameter("issueId"));
		Issue issue = issueService.getIssue(issueId);
		System.out.println("user id: " + userId + "  reporter id: " + issue.getReporter().getId());
		if (userId != issue.getReporter().getId()) {
			modelAndView.setViewName("redirect:/common/home#!/issueView/" + issueId);
			return modelAndView;
		}
		String priority = request.getParameter("priority");
		issue.setPriority(priority);
		issueService.saveIssue(issue);
		modelAndView.setViewName("redirect:/common/home#!/issueView/" + issueId);
		return modelAndView;
	}

	@PostMapping("/showInterest")
	public String showInterest(HttpServletRequest request) {
		int issueId = Integer.parseInt(request.getParameter("issueId"));
		Issue issue = issueService.getIssue(issueId);
		Project project = issue.getSprint().getProject();
		int reporterId = issue.getAsignee().getId();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User asignee = userService.findUserByEmail(auth.getName());
		User reporter = userService.findById(reporterId);
		String reporterEmail = reporter.getEmail();
		String asigneeEmail = asignee.getEmail();
		String asigneeName = asignee.getName();
		String body = asigneeName + " with email: " + asigneeEmail + " wants to make issue: " + issue.getName()
				+ " with id: " + issue.getId() + " in project: " + project.getName() + " project id: "
				+ project.getId();
		System.out.println("Body: " + body);
		new Mail(reporterEmail, "Interest in issue", body);
		return "redirect:/common/home#!/issueView/" + issueId;
	}

	@PostMapping("/deleteIssue")
	public String deleteIssue(HttpServletRequest request) throws AccessDeniedException{
		int userId = (int) request.getSession().getAttribute("user_id");
		int issueId = Integer.parseInt(request.getParameter("issueId"));
		Issue issue = issueService.getIssue(issueId);
                int projectOwnerId = issue.getSprint().getProject().getOwner().getId();
		if (userId != issue.getReporter().getId() && userId != projectOwnerId) {
			throw new AccessDeniedException();
		}
		int sprintId = issue.getSprint().getId();
		issueService.deleteIssue(issueId);
		return "redirect:/common/home#!/sprintView/" + sprintId;
	}
}
