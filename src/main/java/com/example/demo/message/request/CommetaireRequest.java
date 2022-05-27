package com.example.demo.message.request;

public class CommetaireRequest {
	
	public CommetaireRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public CommetaireRequest(String message) {
		super();
		this.message = message;
	}


	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
