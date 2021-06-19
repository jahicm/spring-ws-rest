package com.spring.boot.run.models;

public class UserModel {

	private String password;
	private String username;
	private String authorities;
	private boolean enable;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isEnabled() {
		return enable;
	}

	public void setEnabled(boolean enable) {
		this.enable = enable;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

}
