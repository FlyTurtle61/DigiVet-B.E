package com.springsecurity.basicauthentication.model.request;

public class VetDetailsLoginModel {
	private String email;
	private String password;
	public VetDetailsLoginModel(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public VetDetailsLoginModel() {
		super();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
