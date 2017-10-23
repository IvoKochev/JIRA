/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jira.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ivo
 */
@Entity
@Table(name = "attachments")
public class Attachment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "uploader_id")
	private Integer uploaderid;
	@Column(name = "location")
	private String location;
	@Column(name = "issues_id")
	private Integer issues_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getIssues_id() {
		return issues_id;
	}
	public void setIssues_id(Integer issues_id) {
		this.issues_id = issues_id;
	}
	
    
}
