package com.sub.model;

public class UserPermission {

	private String id;
	private String perm;

	public UserPermission() {}

	public UserPermission(String id, String perm) {
		this.id = id;
		this.perm = perm;
	}

	public String getId() {
		return id;
	}

	public String getPerm() {
		return perm;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPerm(String perm) {
		this.perm = perm;
	}

	@Override
	public String toString() {
		return "UserPermission [id=" + id + ", perm=" + perm + "]";
	}
	

}
