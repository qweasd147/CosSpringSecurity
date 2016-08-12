package com.sub.model;

public class UserVo {
	private String userID;
	private String passWord;
	private String mobile;
	private String address;
	
	public UserVo() {}

	
	
	public UserVo(String userID, String passWord, String mobile, String address) {
		this.userID = userID;
		this.passWord = passWord;
		this.mobile = mobile;
		this.address = address;
	}
	
	public String getUserID() {
		return userID;
	}

	public String getPassWord() {
		return passWord;
	}

	public String getMobile() {
		return mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setAddress(String address) {
		this.address = address;
	}



	@Override
	public String toString() {
		return "UserVo [userID=" + userID + ", passWord=" + passWord + ", mobile=" + mobile + ", address=" + address
				+ "]";
	}
	
}
