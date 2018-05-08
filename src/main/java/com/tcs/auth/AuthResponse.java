package com.tcs.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AuthResponse {

	private String status_cd;
	private String auth_token;
	private String sek;
	
	public AuthResponse(String status_cd, String auth_token, String sek) {
		super();
		this.status_cd = status_cd;
		this.auth_token = auth_token;
		this.sek = sek;
	}
	public AuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getStatus_cd() {
		return status_cd;
	}
	public void setStatus_cd(String status_cd) {
		this.status_cd = status_cd;
	}
	public String getAuth_token() {
		return auth_token;
	}
	public void setAuth_token(String auth_token) {
		this.auth_token = auth_token;
	}
	public String getSek() {
		return sek;
	}
	public void setSek(String sek) {
		this.sek = sek;
	}
	
	@Override
	public String toString() {
		return "AuthResponse [status_cd=" + status_cd + ", auth_token=" + auth_token + ", sek=" + sek + "]";
	}
	
}
