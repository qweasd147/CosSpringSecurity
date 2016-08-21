package com.sub.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class UserPermVo {

	private String userName;
	private String perm;
	
	
	public UserPermVo() {}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPerm() {
		return perm;
	}


	public void setPerm(String perm) {
		this.perm = perm;
	}
	
	
	
}
