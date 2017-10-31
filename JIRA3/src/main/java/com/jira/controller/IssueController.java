package com.jira.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jira.contract.IIssueService;
import com.jira.contract.IProjectService;
import com.jira.contract.ISprintService;
import com.jira.model.Issue;
import com.jira.model.Project;
import com.jira.model.Sprint;
import com.jira.model.User;
import com.jira.repository.IssueRepository;
import com.jira.repository.ProjectRepository;
import com.jira.repository.UserRepository;
import com.jira.service.ProjectServiceImpl;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

@RestController
public class IssueController {
        @Autowired
        private IProjectService projectService;
	@Autowired
	private IIssueService issueService;
	@Autowired
	private ISprintService sprintService;
	@Autowired
	IssueRepository issueRepository;
        @Autowired
        UserRepository userRepository;
        

	@RequestMapping(value = "/common/createIssue", method = RequestMethod.GET)
	public ModelAndView issueCreateTemplate() {
		Issue issue = new Issue();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("issue", issue);
		modelAndView.setViewName("common/createIssue");
		return modelAndView;
	}

	@RequestMapping(value = "/createIssue", method = RequestMethod.POST)
	public ModelAndView createIssue(@ModelAttribute Issue issue, HttpServletRequest request) {
		int user_id = (int) request.getSession().getAttribute("user_id");
		issue.setAsignee_id(user_id);
		issue.setReporter_id(user_id);
		int id = Integer.parseInt(request.getParameter("sprintId"));
		Sprint sprint = sprintService.findSprintById(id);
		issue.setSprint(sprint);
		issue.setStatus("TO DO");
		issueService.saveIssue(issue);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/common/home#!/sprintView/" + id);
		return modelAndView;
	}

	@RequestMapping(value = "/common/issueView", method = RequestMethod.GET)
	public ModelAndView getIssueViewTemplate() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("common/issueView");
		return modelAndView;
	}

	@RequestMapping(value = "/common/issueView/{id}", method = RequestMethod.GET)
	public Issue getIssue(@PathVariable(value = "id") int id) {
		Issue issue = issueRepository.findById(id);
                issue.setAsignee(userRepository.findByid(issue.getAsignee_id()));
                issue.setReporter(userRepository.findByid(issue.getReporter_id()));
		return issue;
	}
        
        @RequestMapping(value = "/setAsignee", method = RequestMethod.POST)
        public ModelAndView setAsignee(HttpServletRequest request) {
            String email = request.getParameter("asignee");
            int id = Integer.parseInt(request.getParameter("issueId"));
            Issue issue = issueService.getIssue(id);
            Sprint sprint = sprintService.findSprintById(issue.getSprint().getId());
            Project project = projectService.getProjectById(sprint.getProject().getId());
            User user = userRepository.findByEmail(email);
            if(user != null && user.getProjects().contains(project)) {
                issue.setAsignee_id(user.getId());
                issueService.saveIssue(issue);
            }
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("redirect:/common/home#!/sprintView/" + id);
            return modelAndView;
        }
       
        @RequestMapping(value = "/setStatus", method = RequestMethod.POST)
        public ModelAndView setStatus(HttpServletRequest request) {
            int id = Integer.parseInt(request.getParameter("issueId"));
            String status = request.getParameter("status");
            Issue issue = issueService.getIssue(id);
            issue.setStatus(status);
            issueService.saveIssue(issue);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("redirect:/common/home#!/issueView/" + id);
            return modelAndView;
        }
        
        @RequestMapping(value = "/setPriority", method = RequestMethod.POST)
        public ModelAndView setPriority(HttpServletRequest request) {
            String priority = request.getParameter("priority");
            int id = Integer.parseInt(request.getParameter("issueId"));
            Issue issue = issueService.getIssue(id);
            issue.setPriority(priority);
            issueService.saveIssue(issue);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("redirect:/common/home#!/issueView/" + id);
            return modelAndView;
        }
}
