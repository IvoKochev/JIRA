package com.jira.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jira.contract.UserService;
import com.jira.file.FileWriter;
import com.jira.model.RatingUser;
import com.jira.model.User;

@RestController
public class AccountController {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping(value = "/common/account", method = RequestMethod.GET)
	public ModelAndView getAccount() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		user.setPassword(null);
		modelAndView.addObject("user", user);
		modelAndView.setViewName("common/account");
		return modelAndView;
	}

	@RequestMapping(value = "/avatar", method = RequestMethod.POST)
	public ModelAndView setAvatar(HttpServletRequest request) throws IOException, ServletException {
		ModelAndView modelAndView = new ModelAndView();
		Part filePart = request.getPart("file");
		String password = request.getParameter("password");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		if (this.bCryptPasswordEncoder.matches(password, user.getPassword())) {
			new FileWriter().avatarWrite(filePart, "avatar.png", user.getId());
			user.setImgurl("/images/" + user.getId() + "/" + "avatar.png");
			user.setPassword(password);
			this.userService.saveUser(user);
		}
		modelAndView.setViewName("redirect:/common/home");
		return modelAndView;
	}

	@RequestMapping(value = "/rating", method = RequestMethod.POST)
	public void setRating(@RequestBody RatingUser data) {
		User user = this.userService.findById(data.getUserId());
		int userCounter = user.getVotecounter();
		double rating = user.getRating();
		double allPoint = userCounter * rating;
		userCounter++;
		allPoint += data.getRatingId();
		double newRating = allPoint / userCounter;
		this.userService.updateUserRating(user.getId(), newRating, userCounter);
	}
}
