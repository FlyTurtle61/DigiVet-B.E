package com.digivet.ws.Shared.dto;

public class UserLoginDto {
	private String email;
	private String password;
	public UserLoginDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public UserLoginDto() {
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
