package com.jira.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jira.cotract.ISprintService;
import com.jira.exceptions.ResourceNotFoundException;
import com.jira.model.Sprint;
import com.jira.repository.SprintRepository;

@Service("sprintService")
public class SprintServiceImpl implements ISprintService{
	@Autowired
	private SprintRepository sprintRepository;

	@Override
	public Sprint findSprintByName(HttpServletRequest request, String sprintName) throws ResourceNotFoundException {
		//findByOwnerid not ready yet
//		int owner_id = (int) request.getSession().getAttribute("user_id");
//		List<Sprint> sprints = sprintRepository.findByOwnerid(owner_id);
//		for(Sprint sprint : sprints) {
//			if(sprint.getName().equals(sprintName)) {
//				return sprint;
//			}
//		}
//		return null;
		return null;
		
	}

	@Override
	public boolean removeSprintByName(String sprintName) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
