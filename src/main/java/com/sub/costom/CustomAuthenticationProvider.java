package com.sub.costom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.sub.model.SecDao;
import com.sub.model.UserPermission;
import com.sub.model.UserVo;

@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	SecDao secDao;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;

		UserVo userVo = null;
		try {
			userVo = findUserById(authToken.getName());
		} catch (Exception e) {logger.debug("Exception error",e);}
		if (userVo == null) {
			throw new UsernameNotFoundException(authToken.getName());
		}

		if (!matchPassword(userVo.getPassWord(), authToken.getCredentials())) {
			throw new BadCredentialsException("not matching username or password");
		}

		List<GrantedAuthority> authorities = null;
		try {
			authorities = getAuthorities(userVo.getUserID());
		} catch (Exception e) {logger.debug("Exception error",e);}
		return new UsernamePasswordAuthenticationToken(
				new UserVo(userVo.getUserID(), null, null, null),null,authorities);
	}

	private UserVo findUserById(String id) throws Exception {
		return secDao.readUserOne(id);
	}

	private List<GrantedAuthority> getAuthorities(String id) throws Exception {
//		List<UserPermission> perms = permMap.get(id);
		List<UserPermission> perms = secDao.readUserPermsOne(id);
		
		if (perms == null)
			return Collections.emptyList();

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(perms.size());
		for (UserPermission perm : perms) {
			authorities.add(new SimpleGrantedAuthority(perm.getPerm()));
		}
		return authorities;
	}

	private boolean matchPassword(String password, Object credentials) {
		return password.equals(credentials);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
