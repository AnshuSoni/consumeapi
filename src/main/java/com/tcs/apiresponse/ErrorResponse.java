package com.tcs.apiresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {
	
	private String status_cd;
	private Error error;
	
	
	public ErrorResponse() {
		
	}
	public String getStatusCd() {
		return status_cd;
	}
	public void setStatusCd(String status_cd) {
		this.status_cd = status_cd;
	}
	public Error getError() {
		return error;
	}
	public void setError(Error error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "ErrorResponse [status_cd=" + status_cd + ", error=" + error + "]";
	}

}
