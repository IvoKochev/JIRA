package com.jira.model;

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
	private Integer id;
	private Integer project_id;
	private Integer sprints_id;
	private String type;
	private String summary;
	private String description;
	private String priority;
	private String status;
	private Integer reporter_id;
	private Integer asignee_id;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "project_id")
	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}


	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "summary")
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "priority")
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "sprints_id")
	public Integer getSprints_id() {
		return sprints_id;
	}

	public void setSprints_id(Integer sprints_id) {
		this.sprints_id = sprints_id;
	}

	@Column(name = "reporter_id")
	public Integer getReporter_id() {
		return reporter_id;
	}

	public void setReporter_id(Integer reporter_id) {
		this.reporter_id = reporter_id;
	}

	@Column(name = "asignee_id")
	public Integer getAsignee_id() {
		return asignee_id;
	}

	public void setAsignee_id(Integer asignee_id) {
		this.asignee_id = asignee_id;
	}
}
