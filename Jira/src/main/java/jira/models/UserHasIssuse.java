package jira.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users_has_issuse")
public class UserHasIssuse {

	private int users_id;
	private int issuse_id;

	public int getUsers_id() {
		return users_id;
	}

	public void setUsers_id(int users_id) {
		this.users_id = users_id;
	}

	public int getIssuse_id() {
		return issuse_id;
	}

	public void setIssuse_id(int issuse_id) {
		this.issuse_id = issuse_id;
	}

}
