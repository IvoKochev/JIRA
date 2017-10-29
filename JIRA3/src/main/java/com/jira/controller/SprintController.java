/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jira.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jira.exceptions.ResourceNotFoundException;
import com.jira.model.Sprint;

/**
 *
 * @author ivo
 */
@RestController
public class SprintController {


	@RequestMapping(value = "/common/createSprint", method = RequestMethod.GET)
	public ModelAndView getSprint() throws ResourceNotFoundException {
		System.err.println("get controller");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("sprint", new Sprint());
		modelAndView.setViewName("common/createSprint");

		return modelAndView;
	}

	@RequestMapping(value = "/common/createSprint", method = RequestMethod.POST)
	public ModelAndView createSprint(@ModelAttribute Sprint sprint) {
		System.out.println("post controller");
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("redirect:/common/projectView");

		return modelAndView;
	}

}
