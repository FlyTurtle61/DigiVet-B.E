package com.digivet.ws.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recommendation")
public class Recommendation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "reported_mail")
	private String reportedMail;
	@Column(name = "description")
	private String description;
	@Column(name = "status")
	private String status;
	@Column(name="response")
	private String response;
	public Recommendation(int id, String reportedMail, String description, String status,String response) {
		super();
		this.id = id;
		this.reportedMail = reportedMail;
		this.description = description;
		this.status = status;
		this.response = response;
	}
	
	public Recommendation() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReportedMail() {
		return reportedMail;
	}
	public void setReportedMail(String reportedMail) {
		this.reportedMail = reportedMail;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	

	
}
