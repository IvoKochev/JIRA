package com.jira.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sprints")
public class Sprint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7483346645618876141L;
	
	private int id;
	private String name;
	private Integer ownerid;
	private String sprintgoal;
	private LocalDateTime start_date;
	private LocalDateTime end_date;

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
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "sprintgoal")
	public String getSprintgoal() {
		return sprintgoal;
	}

	public void setSprintgoal(String sprintgoal) {
		this.sprintgoal = sprintgoal;
	}

	@Column(name = "start_date")
	public LocalDateTime getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDateTime start_date) {
		this.start_date = start_date;
	}

	@Column(name = "end_date")
	public LocalDateTime getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDateTime end_date) {
		this.end_date = end_date;
	}
	
	@Column(name = "owner_id")
	public Integer getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(Integer ownerid) {
		this.ownerid = ownerid;
	}

}
