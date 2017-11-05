package com.jira.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jira.contract.IIssueService;
import com.jira.contract.IProjectService;
import com.jira.contract.UserService;
import com.jira.exceptions.ResourceNotFoundException;
import com.jira.model.Issue;
import com.jira.model.Project;
import com.jira.model.Role;
import com.jira.model.Sprint;
import com.jira.model.User;
import com.jira.repository.RoleRepository;
import com.jira.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private IProjectService projectService;
	@Autowired
	private IIssueService issueService;

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRole("ADMIN");
		user.setRoles(new HashSet<>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public User findById(int id) {
		return this.userRepository.findByid(id);
	}

	@Override
	public void updateUserRating(int id, double rating, int votes) {
		this.userRepository.updateUserRating(id, rating, votes);
	}

	@Override
	public void removeUsersIssues(int userId, int projectId) throws ResourceNotFoundException {
		Project project = projectService.getProjectById(projectId);
		Set<Sprint> sprints = project.getSprints();
		for (Sprint sprint : sprints) {
			Set<Issue> issues = sprint.getIssues();
			for (Iterator<Issue> iterator = issues.iterator(); iterator.hasNext();) {
				Issue issue = iterator.next();
				if (userId == issue.getAsignee().getId() && userId == issue.getReporter().getId()) {
					issueService.deleteIssue(issue.getId());
				}
			}
		}
	}

}
