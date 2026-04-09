package com.api.models;

public class UserLoginCredentials {
	
	private String username;
	private String password;
	public UserLoginCredentials(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserLoginCredentials [username=" + username + ", password=" + password + "]";
	}
	
	

}
