package com.digivet.ws.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "meeting_canceled")
public class MeetingCanceled {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "vet_email")
	private String vetEmail;
	@Column(name = "user_email")
	private String userEmail;
	@Column(name = "description")
	private String description;
	
	
	
	public MeetingCanceled() {
		super();
	}
	public MeetingCanceled(int id, String vetEmail, String userEmail, String description) {
		super();
		this.id = id;
		this.vetEmail = vetEmail;
		this.userEmail = userEmail;
		this.description = description;
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
