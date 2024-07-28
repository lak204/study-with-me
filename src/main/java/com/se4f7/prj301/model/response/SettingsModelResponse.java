package com.se4f7.prj301.model.response;
import com.se4f7.prj301.enums.StatusEnum;
import com.se4f7.prj301.model.BaseModel;

public class SettingsModelResponse extends BaseModel {
	private String content;
	private StatusEnum status;
	private String type;
	private String image;
	public SettingsModelResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SettingsModelResponse(String content, StatusEnum status, String type, String image) {
		super();
		this.content = content;
		this.status = status;
		this.type = type;
		this.image = image;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	
}
