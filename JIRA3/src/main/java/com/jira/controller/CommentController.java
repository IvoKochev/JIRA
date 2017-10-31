/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jira.controller;

import com.jira.contract.ICommentsService;
import com.jira.contract.IIssueService;
import com.jira.model.Comments;
import com.jira.model.Issue;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ivo
 */
@RestController
public class CommentController {
	@Autowired
	private IIssueService issueService;

	@Autowired
	private ICommentsService commentService;

	@RequestMapping(value = "/createComment", method = RequestMethod.POST)
	public ModelAndView createComment(HttpServletRequest request) {
		String text = request.getParameter("comment");
		int user_id = (int) request.getSession().getAttribute("user_id");
		int id = Integer.parseInt(request.getParameter("issueId"));
		Comments comment = new Comments();
		comment.setText(text);
		Issue issue = issueService.getIssue(id);
		comment.setIssue(issue);
		comment.setUser_id(user_id);
		commentService.saveComment(comment);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/common/home#!/issueView/" + id);
		return modelAndView;
	}
}
