/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jira.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jira.contract.ICommentsService;
import com.jira.contract.IIssueService;
import com.jira.model.Comments;
import com.jira.model.Issue;

/**
 *
 * @author ivo
 */
@Controller
public class CommentController {
	@Autowired
	private IIssueService issueService;

	@Autowired
	private ICommentsService commentService;

	@RequestMapping(value = "/createComment", method = RequestMethod.POST)
	public String createComment(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("issueId"));
		String text = request.getParameter("comment");
		if(text==null || text.equals("")) {
			return "redirect:/common/home#!/issueView/" + id;
		}
		int user_id = (int) request.getSession().getAttribute("user_id");
		Comments comment = new Comments();
		comment.setText(text);
		Issue issue = issueService.getIssue(id);
		comment.setIssue(issue);
		comment.setUser_id(user_id);
		commentService.saveComment(comment);
		return "redirect:/common/home#!/issueView/" + id;
	}
}
