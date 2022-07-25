package com.digivet.ws.model.response;

import java.util.Date;

public class CommentRest {
	private  int id;
	private String userEmail;
	private String vetEmail;
	private String comment;
	private Date date;
	private String CreateComment;
	public CommentRest(int id,String userEmail, String vetEmail, String comment, Date date, String CreateComment) {
		super();
		this.id = id;
		this.userEmail = userEmail;
		this.vetEmail = vetEmail;
		this.comment = comment;
		this.date = date;
		this.CreateComment = CreateComment;
	}
	public CommentRest() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getCreateComment() {
		return CreateComment;
	}
	public void setCreateComment(String createComment) {
		CreateComment = createComment;
	}
	
	
}
