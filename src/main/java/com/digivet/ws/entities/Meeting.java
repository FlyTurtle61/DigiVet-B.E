package com.digivet.ws.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "meeting")
public class Meeting {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	@Column(name="user_first_name")
	private String userFirstName;
	@Column(name="user_last_name")
	private String userLastName;
	@Column(name="vet_first_name")
	private String vetFirstName;
	@Column(name="vet_last_name")
	private String vetLastName;
	@Column(name="meeting_type")
	private String meetingType;
	@Column(name="meeting_date")
	private String meetingDate;
	@Column(name = "vet_email")
	private String vetEmail;
	@Column(name = "user_email")
	private String userEmail;
	
	
	public Meeting(int id, String userFirstName, String userLastName, String vetFirstName, String vetLastName,
			String meetingType, String meetingDate,String userEmail,String vetEmail) {
		super();
		this.id = id;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.vetFirstName = vetFirstName;
		this.vetLastName = vetLastName;
		this.meetingType = meetingType;
		this.meetingDate = meetingDate;
		this.vetEmail = vetEmail;
		this.userEmail = userEmail;
	}
	public Meeting() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public String getVetFirstName() {
		return vetFirstName;
	}
	public void setVetFirstName(String vetFirstName) {
		this.vetFirstName = vetFirstName;
	}
	public String getVetLastName() {
		return vetLastName;
	}
	public void setVetLastName(String vetLastName) {
		this.vetLastName = vetLastName;
	}
	public String getMeetingType() {
		return meetingType;
	}
	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}
	public String getMeetingDate() {
		return meetingDate;
	}
	public void setMeetingDate(String meetingDate) {
		this.meetingDate = meetingDate;
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
	
	
	
}
