package com.tcs.auth.datasource;

public class ReturnDataSource {
	private String stateCd;
	private String fileCountUrl;
	private String action;
	public String getStateCd() {
		return stateCd;
	}
	public void setStateCd(String stateCd) {
		this.stateCd = stateCd;
	}
	public String getFileCountUrl() {
		return fileCountUrl;
	}
	public void setFileCountUrl(String fileCountUrl) {
		this.fileCountUrl = fileCountUrl;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	@Override
	public String toString() {
		return "ReturnDataSource [stateCd=" + stateCd + ", fileCountUrl=" + fileCountUrl + ", action=" + action + "]";
	}
	
	
}
