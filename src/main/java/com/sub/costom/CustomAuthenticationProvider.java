package com.sub.costom;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.sub.model.UserVo;


@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	CostomUserDetailsService costomUserDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;

		System.out.println("authenticate()");
		System.out.println("authenticate()");
		System.out.println("authenticate()");
		
		UserVo userVo = null;
		try {
//			userVo = findUserById(authToken.getName());
			userVo = (UserVo) costomUserDetailsService.loadUserByUsername(authToken.getName());
		} catch (Exception e) {logger.debug("Exception error",e);}
		
		if (userVo == null) {
			throw new UsernameNotFoundException(authToken.getName());
		}

		if (!matchPassword(userVo.getPassword(), authToken.getCredentials())) {
			System.out.println("비밀번호 틀림");
			System.out.println("비밀번호 틀림");
			System.out.println("비밀번호 틀림");
			System.out.println("비밀번호 틀림");
			System.out.println("비밀번호 틀림");
			throw new BadCredentialsException("not matching username or password");
		}

		Collection<? extends GrantedAuthority> authorities = null;
		try {
			authorities = userVo.getAuthorities();
		} catch (Exception e) {logger.debug("Exception error",e);}
		return new UsernamePasswordAuthenticationToken(
				null,null,authorities);
	}


	private boolean matchPassword(String password, Object credentials) {
		return password.equals(credentials);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
