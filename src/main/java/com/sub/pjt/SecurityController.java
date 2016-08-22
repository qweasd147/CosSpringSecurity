package com.sub.pjt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

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
	public String userInfo(){
//		"redirect:/result?result=success"
		return "result";
	}
}
