package com.jira.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users_projects")
public class UserHasProject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8970766848065036294L;
	private int user_id;

	private int project_id;
	//ID

	@Column(name = "user_id")
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Column(name = "project_id")
	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
}
