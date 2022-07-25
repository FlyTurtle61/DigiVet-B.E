package com.springsecurity.basicauthentication.model.request;

import java.util.Date;

public class CommentDetailsRequestModel {

	private String userEmail;
	private String vetEmail;
	private String comment;
	private Date date;
	public CommentDetailsRequestModel(String userEmail, String vetEmail, String comment, Date date) {
		super();
		this.userEmail = userEmail;
		this.vetEmail = vetEmail;
		this.comment = comment;
		this.date = date;
	}
	public CommentDetailsRequestModel() {
		super();
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getVetEmail() {
		return vetEmail;
	}
	public void setVetEmail(String vetEmail) {
		this.vetEmail = vetEmail;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
	
}
