package jira.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "issues")
public class Issue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5899221217986589147L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "project_id")
	private int project_id;
	@Column(name = "sprint_id")
	private Integer sprint_id;
	@Column(name = "type")
	private String type;
	@Column(name = "summary")
	private String summary;
	@Column(name = "description")
	private String description;
	@Column(name = "priority")
	private String priority;
	@Column(name = "status")
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public Integer getSprint_id() {
		return sprint_id;
	}

	public void setSprint_id(Integer sprint_id) {
		this.sprint_id = sprint_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
