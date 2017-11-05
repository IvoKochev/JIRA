package com.jira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@ComponentScan({"com.jira.controller","com.jira.service","com.jira.configuration"})
@SpringBootApplication
public class JiraApplication {
	public static void main(String[] args) {
		SpringApplication.run(JiraApplication.class, args);
	}
}
