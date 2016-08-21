package com.sub.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("secDao")
public class SecDaoImp implements SecDao{

	@Autowired
	SqlSession sqlSession;

	@Override
	public UserVvo readUserOne(String userID) throws Exception {
		return sqlSession.selectOne("sec.readUserOne", userID);
	}
	
	@Override
	public List<UserPermVo> readUserPermsOne(String userID) throws Exception {
		return sqlSession.selectList("sec.readUserPermsOne", userID);
	}

	

}
