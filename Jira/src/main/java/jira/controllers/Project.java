package jira.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
public class Project {

	@RequestMapping("/test")
	public String test() {
		return "Test1";
	}

}
