package com.digivet.ws.Shared.dto;

public class RecommendationDto {
	private int id;	
	private String reportedMail;
	private String description;
	private String status;
	private String response;
	public RecommendationDto(int id, String reportedMail, String description, String status,String response) {
		super();
		this.id = id;
		this.reportedMail = reportedMail;
		this.description = description;
		this.status = status;
		this.response = response;
	}
	public RecommendationDto() {
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
