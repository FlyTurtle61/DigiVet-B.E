package com.digivet.ws.model.request;

import javax.persistence.Column;

public class MeetingCanceledRequestModel {
	private int id;
	private String vetEmail;
	private String userEmail;
	private String description;
	public MeetingCanceledRequestModel(int id,String vetEmail, String userEmail, String description) {
		super();
		this.id = id;
		this.vetEmail = vetEmail;
		this.userEmail = userEmail;
		this.description = description;
	}
	public MeetingCanceledRequestModel() {
		super();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVetEmail() {
		return vetEmail;
	}
	public void setVetEmail(String vetEmail) {
		this.vetEmail = vetEmail;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
