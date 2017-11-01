package com.jira.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jira.contract.UserService;
import com.jira.model.User;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping(value = { "/", "/login" })
	public String login() {
		return "login";
	}

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}

	@PostMapping("/registration")
	public String createNewUser(Model model, @Valid User user, final BindingResult bindingResult) {
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user",
					"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		user.setImgurl("/images/account.png");
		userService.saveUser(user);
		model.addAttribute("successMessage", "User has been registered successfully");
		model.addAttribute("user", new User());
		return "registration";
	}

	@GetMapping("/common/home")
	public String adminHome(Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		request.getSession().setAttribute("user_id", user.getId());
		String s = auth.getAuthorities().toString();
		if (s.contains("ADMIN")) {
			model.addAttribute("isAdmin", true);
		} else {
			model.addAttribute("isAdmin", false);
		}
		model.addAttribute("userImg", "" + user.getImgurl());
		model.addAttribute("userEmail", "" + " (" + user.getEmail() + ")");
		model.addAttribute("userName", "Welcome " + user.getName());
		return "common/home";
	}
}
