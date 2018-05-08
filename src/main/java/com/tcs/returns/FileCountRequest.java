package com.tcs.returns;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(ignoreUnknown=true)
public class FileCountRequest {
	
	private String action;
	private String type;
	private String state_cd;
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
	private String date;

	
	public FileCountRequest() {
		super();
		// TODO Auto-generated constructor stub
	}


	public FileCountRequest(String action, String type, String state_cd, String date) {
		super();
		this.action = action;
		this.type = type;
		this.state_cd = state_cd;
		this.date = date;
	}


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getState_cd() {
		return state_cd;
	}


	public void setState_cd(String state_cd) {
		this.state_cd = state_cd;
	}

	
	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "FileCountRequest [action=" + action + ", type=" + type + ", state_cd=" + state_cd + ", date=" + date
				+ "]";
	}

}
