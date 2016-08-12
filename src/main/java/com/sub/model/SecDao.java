package com.sub.model;

import java.util.List;


public interface SecDao {
	
	public UserVo readUserOne(String userID) throws Exception;
	public List<UserPermission> readUserPermsOne(String userID) throws Exception;
}
