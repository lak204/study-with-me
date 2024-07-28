package com.se4f7.prj301.model.request;



public class SettingsModelRequest {
	private String content;
	private String type;
	private String image;
	public SettingsModelRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SettingsModelRequest(String content, String type, String image) {
		super();
		this.content = content;	
		this.type = type;
		this.image = image;		
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
