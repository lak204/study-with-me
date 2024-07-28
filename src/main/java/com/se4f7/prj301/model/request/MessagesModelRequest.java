package com.se4f7.prj301.model.request;

public class MessagesModelRequest {
	private String subject;
	private String email;
	private String message;
	public MessagesModelRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MessagesModelRequest(String subject, String email, String message) {
		super();
		this.subject = subject;
		this.email = email;
		this.message = message;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
