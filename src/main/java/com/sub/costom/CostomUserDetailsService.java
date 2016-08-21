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

import com.sub.model.SecDao;
import com.sub.model.UserPermVo;
import com.sub.model.UserVvo;

//@Service("costomUserDetailsService")
public class CostomUserDetailsService implements UserDetailsService {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	SecDao secDao;

	public void setSecDao(SecDao secDao) {
		this.secDao = secDao;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername() 실행");
		System.out.println("loadUserByUsername() 실행");
		System.out.println("loadUserByUsername() 실행");
		
		UserVvo userVo = null;
		List<UserPermVo> perms = null;
		List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		try {

			userVo = secDao.readUserOne(userName);
			perms = secDao.readUserPermsOne(userVo.getUsername());
			
			if(userVo == null || perms == null){
				System.out.println("userVo == null || perms == null");
				throw new UsernameNotFoundException(userName);
			}
			for (UserPermVo perm : perms) {
				auth.add(new SimpleGrantedAuthority(perm.getPerm()));
			}
		}
		catch (UsernameNotFoundException e) {	System.out.println("UsernameNotFoundException"); throw e;	}
		catch (Exception e) {	System.out.println(e.getMessage());	}
		
		System.out.println("리턴");
		System.out.println(userVo.getUsername());
		System.out.println(userVo.getPassword());
		System.out.println(auth.toString());
		System.out.println("보유 권한");
		for (UserPermVo perm : perms) {
			System.out.println(perm.getPerm());
		}
		
//		return new User(userVo.getUsername(), userVo.getPassword(), auth);
		return new User(userVo.getUsername(), userVo.getPassword(), true, true, true, true, auth);
	}

}
