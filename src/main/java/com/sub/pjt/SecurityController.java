package com.sub.pjt;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sub.model.UserVo;
import com.sub.service.SecurityService;

@Controller
public class SecurityController {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	SecurityService securityService;
	
	@RequestMapping("/index")
	public String mainIndex(){
		return "mainIndex";
	}
	
	@RequestMapping("/loginForm")
	public String loginForm(){
		return "loginForm";
	}
	
	@RequestMapping("/admin/result")
	public String adminResult(){
		
		return "result";
	}
	
	@RequestMapping("/user/result")
	public String userResult(){
//		"redirect:/result?result=success"
		return "result";
	}
	
	@RequestMapping("/userInfo")
	public String userInfo(ModelMap model, Authentication authentication){
		UserVo userVo = null;
		if(!(authentication == null)){
			userVo = (UserVo) authentication.getPrincipal();
			model.addAttribute("userVo", userVo);
		}
		return "userinfo";
	}
	
	@RequestMapping("/encodingForm")
	public String encodingForm(){
	    return "encodingForm";
	}
	
	@RequestMapping("/pwencode")
	public String passwordEncoder(@RequestParam(value="targetStr", required=false, defaultValue="") String targetStr, ModelMap model){
		Map<String, String> map = null;
		try {
			map = securityService.passwordEncoding(targetStr);
		} catch (Exception e) { logger.debug("Excepotion!! {}",e);}
		
		model.addAttribute("targetStr", map.get("targetStr"));
		model.addAttribute("bCryptString", map.get("bCryptString"));
		
	    return "viewEncoded";
	}
}
