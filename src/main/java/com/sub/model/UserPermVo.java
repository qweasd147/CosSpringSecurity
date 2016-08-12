package com.sub.model;

public class UserPermVo extends UserVo{
	private String perm;

	public UserPermVo() {}
	
	
	public UserPermVo(String userID, String passWord, String mobile, String address, String perm) {
		super(userID, passWord, mobile, address);
		this.perm = perm;
	}
	
	public String getPerm() {
		return perm;
	}

	public void setPerm(String perm) {
		this.perm = perm;
	}


	@Override
	public String toString() {
		super.toString();
		return "UserPermVo [perm=" + perm + "]";
	}
	
}
