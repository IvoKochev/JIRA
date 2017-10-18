package jira.contracts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jira.exceptions.ResourceNotFoundException;
import jira.models.Project;

public interface IProjectService {

	List<Project> getProjectList(HttpServletRequest request) throws ResourceNotFoundException;

}
