package com.sub.model;

public class UserVvo {
	
	private String username;
	private String password;
	private String mobile;
	private String address;
	private String email;
	private boolean enabled; 
	
	public UserVvo() {}

	public UserVvo(String username, String password, String mobile, String address, String email, boolean enabled) {
		this.username = username;
		this.password = password;
		this.mobile = mobile;
		this.address = address;
		this.email = email;
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		System.out.println("setUsername");
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		System.out.println("setPassword");
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		System.out.println("setMobile");
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "UserVvo [username=" + username + ", password=" + password + ", mobile=" + mobile + ", address="
				+ address + ", email=" + email + ", enabled=" + enabled + "]";
	}
	
	
	
	
}
