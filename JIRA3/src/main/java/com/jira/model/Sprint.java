package com.jira.model;

import java.io.Serializable;
import java.util.Date;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sprints")
public class Sprint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7483346645618876141L;

	private int id;
	private String name;
	private int owner_id;
	private String sprintgoal;
	private Date start_date;
	private Date end_date;
	private Project project;
	private String imgurl;
	private Set<Issue> issues;
	@Transient
	private String startDate;
	@Transient
	private String endDate;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name")
	@NotEmpty(message = "*Please provide a name")
	@Length(max = 30, message = "*Sprint name must have at max 30 characters")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "sprintgoal")
	@NotEmpty(message = "*Please provide a sprint goal")
	public String getSprintgoal() {
		return sprintgoal;
	}

	public void setSprintgoal(String sprintgoal) {
		this.sprintgoal = sprintgoal;
	}

	@Column(name = "start_date")
	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	@Column(name = "end_date")
	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	@Column(name = "owner_id")
	public int getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}

	@ManyToOne
	@JoinColumn(name = "projects_id")
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Column(name = "imgurl")
	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end_date == null) ? 0 : end_date.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sprintgoal == null) ? 0 : sprintgoal.hashCode());
		result = prime * result + ((start_date == null) ? 0 : start_date.hashCode());
		return result;
	}

	@OneToMany(mappedBy = "sprint")
	@JsonIgnore
	public Set<Issue> getIssues() {
		return issues;
	}

	public void setIssues(Set<Issue> issues) {
		this.issues = issues;
	}

	@Transient
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Transient
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sprint other = (Sprint) obj;
		if (end_date == null) {
			if (other.end_date != null)
				return false;
		} else if (!end_date.equals(other.end_date))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sprintgoal == null) {
			if (other.sprintgoal != null)
				return false;
		} else if (!sprintgoal.equals(other.sprintgoal))
			return false;
		if (start_date == null) {
			if (other.start_date != null)
				return false;
		} else if (!start_date.equals(other.start_date))
			return false;
		return true;
	}

}
