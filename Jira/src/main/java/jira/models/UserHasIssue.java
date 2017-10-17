package jira.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users_has_issues")
public class UserHasIssue {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "users_id")
	private int users_id;
	@Column(name = "issuse_id")
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
