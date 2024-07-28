package com.se4f7.prj301.model.response;

import com.se4f7.prj301.enums.StatusEnum;
import com.se4f7.prj301.model.BaseModel;

public class MessagesModelResponse extends BaseModel {
 private String subject;
 private String email;
 private StatusEnum status;
 private String message;
 public MessagesModelResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
public MessagesModelResponse(String subject, String email, StatusEnum status, String message) {
	super();
	this.subject = subject;
	this.email = email;
	this.status = status;
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
public StatusEnum getStatus() {
	return status;
}
public void setStatus(StatusEnum status) {
	this.status = status;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
 
}
