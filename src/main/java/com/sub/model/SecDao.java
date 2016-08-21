package com.sub.model;

import java.util.List;


public interface SecDao {
	
	public UserVvo readUserOne(String userID) throws Exception;
	public List<UserPermVo> readUserPermsOne(String userID) throws Exception;
}
