package com.digivet.ws.Shared.dto;

public class VetLoginDto {
	private String email;
	private String password;
	public VetLoginDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public VetLoginDto() {
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
