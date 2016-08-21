package com.sub.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserVo extends User{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1140115440788810336L;
	private String mobile;
	private String address;
	private String email;
	
	public UserVo(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, String mobile, String address, String email) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.mobile = mobile;
		this.address = address;
		this.email = email;
	}

	public UserVo(String username,String password,String mobile,String address, String email, String enabled) {
//		super(username, password, Boolean.valueOf(enabled).booleanValue(), true, true, true, null);
		super(username, password, true, true, true, true, null);
		this.mobile = mobile;
		this.address = address;
		this.email = email;
	}
	
	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
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
	
	
}
