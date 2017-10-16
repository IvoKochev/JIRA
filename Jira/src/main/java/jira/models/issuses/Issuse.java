package jira.models.issuses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "issuse")
public class Issuse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "projects_id")
	private int projects_id;
	@Column(name = "sprints_id")
	private int sprints_id;
	@Column(name = "type")
	private String type;
	@Column(name = "summary")
	private String summary;
	@Column(name = "description")
	private String description;
	@Column(name = "priority")
	private String priority;
	@Column(name = "attachment")
	private String attachment;
	@Column(name = "status")
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProject_id() {
		return projects_id;
	}

	public void setProject_id(int project_id) {
		this.projects_id = project_id;
	}

	public int getSprint_id() {
		return sprints_id;
	}

	public void setSprint_id(int sprint_id) {
		this.sprints_id = sprint_id;
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

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
