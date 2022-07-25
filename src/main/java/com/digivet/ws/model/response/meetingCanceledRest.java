package com.digivet.ws.model.response;

public class meetingCanceledRest {
	private String vetEmail;
	private String userEmail;
	private String description;
	
	
	public meetingCanceledRest() {
		super();
	}
	public meetingCanceledRest(String vetEmail, String userEmail, String description) {
		super();
		this.vetEmail = vetEmail;
		this.userEmail = userEmail;
		this.description = description;
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
