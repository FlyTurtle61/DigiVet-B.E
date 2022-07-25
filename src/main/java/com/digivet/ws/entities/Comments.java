package com.digivet.ws.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "comment")
public class Comments {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name ="user_email")
	private String userEmail;
	@Column(name="vet_email")
	private String vetEmail;
	@Column(name="comment")
	private String comment;
	@Column(name="date")
	private Date date;
	@Column(name = "create_comment")
	private String CreateComment;
	
	public Comments() {
		super();
	}
	public Comments(int id, String userEmail, String vetEmail, String comment, Date date,String CreateComment) {
		super();
		this.id = id;
		this.userEmail = userEmail;
		this.vetEmail = vetEmail;
		this.comment = comment;
		this.date = date;
		this.CreateComment = CreateComment;
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