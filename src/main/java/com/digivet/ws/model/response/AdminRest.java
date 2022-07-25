package com.digivet.ws.model.response;

public class AdminRest {
	private int id;
	private String fullName;
	private String adminEmail;
	private String password;
	public AdminRest(int id, String fullName, String adminEmail, String password) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.adminEmail = adminEmail;
		this.password = password;
	}
	public AdminRest() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
