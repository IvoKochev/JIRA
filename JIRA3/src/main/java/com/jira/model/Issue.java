
package com.jira.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "issues")
public class Issue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5899221217986589147L;
	private int id;
	private String name;
	private Sprint sprint;
	private String type;
	private String summary;
	private String description;
	private String priority;
	private String status;
	private User asignee;
	private User reporter;
	private String imgUrl;
	private Set<Attachment> attachments = new HashSet<>();
	private Set<Comments> comments = new HashSet<>();
	@Transient
	private int userId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "summary")
	@NotEmpty(message = "*Please, provide summary")
	@Length(max = 40)
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

	@OneToMany(mappedBy = "issue")
	public Set<Comments> getComments() {
		return comments;
	}

	public void setComments(Set<Comments> comments) {
		this.comments = comments;
	}

	@Column(name = "name")
	@NotEmpty
	@Length(max = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "issue")
	public Set<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}

	@ManyToOne
	@JoinColumn(name = "sprints_id")
	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "asignee_id")
	public User getAsignee() {
		return asignee;
	}

	public void setAsignee(User asignee) {
		this.asignee = asignee;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "reporter_id")
	public User getReporter() {
		return reporter;
	}

	public void setReporter(User reporter) {
		this.reporter = reporter;
	}

	@Column(name = "img_url")
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Transient
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	// @Override
	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = prime * result + ((attachments == null) ? 0 :
	// attachments.hashCode());
	// result = prime * result + ((comments == null) ? 0 : comments.hashCode());
	// result = prime * result + ((description == null) ? 0 :
	// description.hashCode());
	// result = prime * result + ((priority == null) ? 0 : priority.hashCode());
	// result = prime * result + ((status == null) ? 0 : status.hashCode());
	// result = prime * result + ((summary == null) ? 0 : summary.hashCode());
	// result = prime * result + ((type == null) ? 0 : type.hashCode());
	// return result;
	// }
	//
	// @Override
	// public boolean equals(Object obj) {
	// if (this == obj)
	// return true;
	// if (obj == null)
	// return false;
	// if (getClass() != obj.getClass())
	// return false;
	// Issue other = (Issue) obj;
	// if (attachments == null) {
	// if (other.attachments != null)
	// return false;
	// } else if (!attachments.equals(other.attachments))
	// return false;
	// if (comments == null) {
	// if (other.comments != null)
	// return false;
	// } else if (!comments.equals(other.comments))
	// return false;
	// if (description == null) {
	// if (other.description != null)
	// return false;
	// } else if (!description.equals(other.description))
	// return false;
	//
	// if (priority == null) {
	// if (other.priority != null)
	// return false;
	// } else if (!priority.equals(other.priority))
	// return false;
	// if (status == null) {
	// if (other.status != null)
	// return false;
	// } else if (!status.equals(other.status))
	// return false;
	// if (summary == null) {
	// if (other.summary != null)
	// return false;
	// } else if (!summary.equals(other.summary))
	// return false;
	// if (type == null) {
	// if (other.type != null)
	// return false;
	// } else if (!type.equals(other.type))
	// return false;
	// return true;
	// }

}
