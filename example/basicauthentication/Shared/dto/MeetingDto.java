package com.springsecurity.basicauthentication.Shared.dto;

import java.util.Date;

public class MeetingDto {

	private int id;
	private String userFirstName;
	private String userLastName;
	private String vetFirstName;
	private String vetLastName;
	private String meetingType;
	private String meetingDate;
	private String vetEmail;
	private String userEmail;
	public MeetingDto(int id, String userFirstName, String userLastName, String vetFirstName, String vetLastName,
			String meetingType, String meetingDate,String vetEmail,String userEmail) {
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
	public MeetingDto() {
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
