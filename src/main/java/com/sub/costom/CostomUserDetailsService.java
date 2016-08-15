package com.sub.costom;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sub.model.SecDao;
import com.sub.model.UserPermission;
import com.sub.model.UserVo;

//@Service("costomUserDetailsService")
public class CostomUserDetailsService implements UserDetailsService {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	SecDao secDao;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername() 실행");
		System.out.println("loadUserByUsername() 실행");
		System.out.println("loadUserByUsername() 실행");
		
		UserVo userVo = null;
		List<UserPermission> perms = null;
		List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		System.out.println(111111111);
		try {
			userVo = secDao.readUserOne(userName);
			perms = secDao.readUserPermsOne(userVo.getUserID());
			
			if(userVo == null || perms == null)
				throw new UsernameNotFoundException(userName);
			
			for (UserPermission perm : perms) {
				auth.add(new SimpleGrantedAuthority(perm.getPerm()));
			}
		} catch (UsernameNotFoundException e) {
			throw e;
		} catch (Exception e) {
			logger.debug("Exception error",e);
		}
		return new User(userName, userVo.getPassWord(), auth);
	}

}
