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
	
	private Integer id;
	private Integer uploader_id;
	private String location;
	private Integer issues_id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "location")
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Column(name = "issues_id")
	public Integer getIssues_id() {
		return issues_id;
	}
	
	public void setIssues_id(Integer issues_id) {
		this.issues_id = issues_id;
	}
	
	@Column(name = "uploader_id")
	public Integer getUploaderid() {
		return uploader_id;
	}
	public void setUploaderid(Integer uploaderid) {
		this.uploader_id = uploaderid;
	}
	
    
}
