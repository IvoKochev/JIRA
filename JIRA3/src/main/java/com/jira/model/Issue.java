
package com.jira.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private Set<Attachment> attachments = new HashSet<>();
	private Set<Comments> comments = new HashSet<>();
	
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
	@NotEmpty(message = "*Please, provide type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "summary")
	@NotEmpty(message = "*Please, provide summary")
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
	@NotEmpty(message = "Please, provide priority")
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

	@OneToMany(mappedBy = "issue")
	@Fetch(FetchMode.JOIN)
    @JsonIgnore
	public Set<Comments> getComments() {
		return comments;
	}
	
	public void setComments(Set<Comments> comments) {
		this.comments = comments;
	}

	@OneToMany(mappedBy = "issue")
	@Fetch(FetchMode.JOIN)
    @JsonIgnore
	public Set<Attachment> getAttachments() {
		return attachments;
	}

	
	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asignee_id == null) ? 0 : asignee_id.hashCode());
		result = prime * result + ((attachments == null) ? 0 : attachments.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((project_id == null) ? 0 : project_id.hashCode());
		result = prime * result + ((reporter_id == null) ? 0 : reporter_id.hashCode());
		result = prime * result + ((sprints_id == null) ? 0 : sprints_id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Issue other = (Issue) obj;
		if (asignee_id == null) {
			if (other.asignee_id != null)
				return false;
		} else if (!asignee_id.equals(other.asignee_id))
			return false;
		if (attachments == null) {
			if (other.attachments != null)
				return false;
		} else if (!attachments.equals(other.attachments))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (project_id == null) {
			if (other.project_id != null)
				return false;
		} else if (!project_id.equals(other.project_id))
			return false;
		if (reporter_id == null) {
			if (other.reporter_id != null)
				return false;
		} else if (!reporter_id.equals(other.reporter_id))
			return false;
		if (sprints_id == null) {
			if (other.sprints_id != null)
				return false;
		} else if (!sprints_id.equals(other.sprints_id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	

	
}

