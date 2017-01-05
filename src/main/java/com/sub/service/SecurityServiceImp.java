package com.sub.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("securityService")
public class SecurityServiceImp implements SecurityService{

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Map<String, String> passwordEncoding(String targetStr) throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		if(StringUtils.hasText(targetStr)){
			// 암호화 작업
			String bCryptString = passwordEncoder.encode(targetStr);
			map.put(INPUTPASSWORD, targetStr);
			map.put(ENCODEDPASSWORD, bCryptString);
		}else{
			map.put(INPUTPASSWORD, INPUTERR);
			map.put(ENCODEDPASSWORD, ECODEDERR);
		}
		return map;
	}

}
