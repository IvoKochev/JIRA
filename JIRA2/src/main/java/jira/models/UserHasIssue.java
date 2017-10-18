package jira.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "issues_has_users")
public class UserHasIssue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 120723788453304824L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "user_id")
	private int user_id;
	@Column(name = "issue_id")
	private int issue_id;

	public int getUsers_id() {
		return user_id;
	}

	public void setUsers_id(int users_id) {
		this.user_id = users_id;
	}

	public int getIssue_id() {
		return issue_id;
	}

	public void setIssue_id(int issue_id) {
		this.issue_id = issue_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
