package com.tcs.apiresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown =  true)
public class Error {
	
	private String error_cd;
	private String message;
	
	
	public Error() {
		
	}
	
	public String getError_cd() {
		return error_cd;
	}

	public void setError_cd(String error_cd) {
		this.error_cd = error_cd;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Error [error_cd=" + error_cd + ", message=" + message + "]";
	}
	
}
