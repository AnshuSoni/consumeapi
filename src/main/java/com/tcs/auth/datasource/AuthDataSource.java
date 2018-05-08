package com.tcs.auth.datasource;

public class AuthDataSource {
	private String clientId;
	private String clientSecret;
	private String password;
	private String stateCode;
	private String authURL;
	private String username;
	private String action;
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getAuthURL() {
		return authURL;
	}
	public void setAuthURL(String authURL) {
		this.authURL = authURL;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	@Override
	public String toString() {
		return "AuthDataSource [clientId=" + clientId + ", clientSecret=" + clientSecret + ", password=" + password
				+ ", stateCode=" + stateCode + ", authURL=" + authURL + ", username=" + username + ", action=" + action
				+ "]";
	}
	
	
}
