package jira.services;

import java.util.List;

import org.springframework.stereotype.Service;

import jira.contracts.IProjectService;
import jira.models.Project;

@Service
public class DbProjectService implements IProjectService {

	@Override
	public List<Project> getProjectList() {
		// TODO Auto-generated method stub
		return null;
	}

}
