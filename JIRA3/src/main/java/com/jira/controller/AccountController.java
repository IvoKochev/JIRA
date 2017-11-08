package com.jira.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jira.contract.UserService;
import com.jira.error.AbstractError;
import com.jira.exceptions.AccessDeniedException;
import com.jira.file.FileWriter;
import com.jira.model.RatingUser;
import com.jira.model.User;

@Controller
public class AccountController extends AbstractError {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/common/account")
	public String getAccount(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		user.setPassword(null);
		model.addAttribute("user", user);
		return "common/account";
	}

	@PostMapping(value = "/avatar")
	public String setAvatar(HttpServletRequest request) throws IOException, ServletException, AccessDeniedException {
		Part filePart = request.getPart("file");
		String password = request.getParameter("password");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		if (!this.bCryptPasswordEncoder.matches(password, user.getPassword())) {
			throw new AccessDeniedException();
		}
		new FileWriter().avatarWrite(filePart, "avatar.png", user.getId());
		user.setImgurl("/images/" + user.getId() + "/" + "avatar.png");
		user.setPassword(password);
		this.userService.saveUser(user);
		return "redirect:/common/home";
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String changePassword(Model model, HttpServletRequest request) throws AccessDeniedException {
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmpassword");
		if (oldPassword == null || newPassword == null || confirmPassword == null
				|| !newPassword.equals(confirmPassword)) {

			throw new AccessDeniedException();
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		if (!this.bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
			throw new AccessDeniedException();
		}

		user.setPassword(newPassword);
		this.userService.saveUser(user);

		return "redirect:/common/home";
	}

	@PostMapping(value = "/rating")
	public String setRating(@RequestBody RatingUser data) {
		User user = this.userService.findById(data.getUserId());
		int userCounter = user.getVotecounter();
		double rating = user.getRating();
		double allPoint = userCounter * rating;
		userCounter++;
		allPoint += data.getRatingId();
		double newRating = allPoint / userCounter;
		this.userService.updateUserRating(data.getUserId(), newRating, userCounter);
		return "/common/projectView";
	}

}
