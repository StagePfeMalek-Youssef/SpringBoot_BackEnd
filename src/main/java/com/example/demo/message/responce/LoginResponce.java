package com.example.demo.message.responce;

public class LoginResponce {
	  private String email;
	  private String token;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LoginResponce() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginResponce(String email, String token) {
		super();
		this.email = email;
		this.token = token;
	}
	  
	  
	  
}
