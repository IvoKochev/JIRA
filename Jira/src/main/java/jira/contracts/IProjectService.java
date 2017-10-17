package jira.contracts;

import java.util.List;

import jira.models.Project;

public interface IProjectService {

	List<Project> getProjectList();

}
