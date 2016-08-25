package com.sub.service;

import java.util.Map;

public interface SecurityService {
	/**
	 * 입력된 값(targetStr)을 암호화 하여 "targetStr" : "(입력한 값)", "bCryptString" : "(암호화 된 값)"으로 반환
	 * @param targetStr
	 * @param bCryptString
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> passwordEncoding(String targetStr) throws Exception;
}
