package com.jira.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jira.contract.IAttachmentService;
import com.jira.contract.IIssueService;
import com.jira.contract.UserService;
import com.jira.file.FileWriter;
import com.jira.model.Attachment;
import com.jira.model.Issue;
import com.jira.model.User;

@Controller
public class AttachmentController {
	@Autowired
	private IIssueService issueService;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private UserService userService;

	@PostMapping("/addAttachment")
	public String addAttachment(HttpServletRequest request) throws IOException, ServletException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		int issueId = (int) Integer.parseInt(request.getParameter("issueId"));

		Attachment attachment = new Attachment();
		Issue issue = issueService.getIssue(issueId);
		if (user.getId() != issue.getAsignee().getId() && user.getId() != issue.getReporter().getId()) {
			return "redirect:/common/home#!/issueView/" + issueId;
		}
		Part partFile = request.getPart("attachment");
		String attachmentName = partFile.getSubmittedFileName();
		if (partFile.getSize() == 0 || attachmentName == null || attachmentName.isEmpty()) {
			return "redirect:/common/home#!/issueView/" + issueId;
		}
		int maxId = attachmentService.findMaxId();
		new FileWriter().writeAttachment(partFile, maxId + 1, user.getId());
		attachment.setName(attachmentName);
		attachment.setOwner(user);
		attachment.setIssue(issue);
		attachment.setLocation(
				"/home/ivo/ivo/Jira/JiraAttachments/" + user.getId() + "/" + (this.attachmentService.findMaxId() + 1));
		attachmentService.uploadAttachment(attachment);
		return "redirect:/common/home#!/issueView/" + issueId;
	}

	@RequestMapping(value = "/getAttachment/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<byte[]> getAttachment(@PathVariable("id") int id) throws IOException {
		return attachmentService.getFile(id);
	}

}
