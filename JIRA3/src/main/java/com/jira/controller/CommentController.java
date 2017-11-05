/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jira.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.jira.contract.ICommentsService;
import com.jira.contract.IIssueService;
import com.jira.contract.UserService;
import com.jira.model.Comments;
import com.jira.model.Issue;
import com.jira.model.User;


@Controller
public class CommentController {
	@Autowired
	private IIssueService issueService;

	@Autowired
	private ICommentsService commentService;
        @Autowired
        private UserService userService;

	@PostMapping("/createComment")
	public String createComment(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("issueId"));
		String text = request.getParameter("comment");
		if (text == null || text.equals("")) {
			return "redirect:/common/home#!/issueView/" + id;
		}
		int user_id = (int)request.getSession().getAttribute("user_id");
                User owner = userService.findById(user_id);
		Comments comment = new Comments();
		comment.setText(text);
		Issue issue = issueService.getIssue(id);
		comment.setIssue(issue);
		comment.setOwner(owner);
		commentService.saveComment(comment);
                System.out.println(comment.getOwner().getName());
		return "redirect:/common/home#!/issueView/" + id;
	}
        
        @PostMapping("/deleteComment")
        public String deleteComment(HttpServletRequest request) {
            int commentId = Integer.parseInt(request.getParameter("commentId"));
            Comments comment = commentService.findComment(commentId);
            int issueId = comment.getIssue().getId();
            commentService.deleteComment(commentId);
            return "redirect:/common/home#!/issueView/" + issueId;
        }
}
