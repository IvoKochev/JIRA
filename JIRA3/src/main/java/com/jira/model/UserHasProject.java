package com.jira.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users_projects")
public class UserHasProject {

	private UserHasProjectId userHasProjectID;

	@EmbeddedId
	public UserHasProjectId getUserHasProjectID() {
		return userHasProjectID;
	}

	public void setUserHasProjectID(UserHasProjectId userHasProjectID) {
		this.userHasProjectID = userHasProjectID;
	}
}
