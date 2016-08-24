package com.sub.costom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import com.sub.model.UserVo;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	private String targetUrlParameter;
	private String defaultUrl;
	private boolean useReferer;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	public CustomAuthenticationSuccessHandler(){
		targetUrlParameter = "";
		defaultUrl = "/";
		useReferer = false;
	}
	
	public String getTargetUrlParameter() {
		return targetUrlParameter;
	}



	public void setTargetUrlParameter(String targetUrlParameter) {
		this.targetUrlParameter = targetUrlParameter;
	}



	public String getDefaultUrl() {
		return defaultUrl;
	}



	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}



	public boolean isUseReferer() {
		return useReferer;
	}



	public void setUseReferer(boolean useReferer) {
		this.useReferer = useReferer;
	}



	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		clearAuthenticationAttributes(request);
		UserVo details = (UserVo) authentication.getPrincipal();
		
		logger.debug("로그인 정보 {}",details.toString());
		
		int intRedirectStrategy = decideRedirectStrategy(request, response);
		switch(intRedirectStrategy){
		case 1:
			logger.info("지정된 Request Parameter(loginRedirect) 우선순위 {}", 1);
			useTargetUrl(request, response);
			break;
		case 2:
			logger.info("세션에 저장된 URL로 redirect 우선순위 {}", 2);
			useSessionUrl(request, response);
			break;
		case 3:
			logger.info("로그인 페이지를 방문하기 전 페이지의 URL 우선순위 {}", 3);
			useRefererUrl(request, response);
			break;
		default:
			logger.info("디폴트 URL 우선순위 {}", 4);
			useDefaultUrl(request, response);
		}
	}
	
	private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
	
	private void useTargetUrl(HttpServletRequest request, HttpServletResponse response) throws IOException{
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if(savedRequest != null){
			requestCache.removeRequest(request, response);
		}
		String targetUrl = request.getParameter(targetUrlParameter);
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	private void useSessionUrl(HttpServletRequest request, HttpServletResponse response) throws IOException{
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		String targetUrl = savedRequest.getRedirectUrl();
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	private void useRefererUrl(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String targetUrl = request.getHeader("REFERER");
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	private void useDefaultUrl(HttpServletRequest request, HttpServletResponse response) throws IOException{
		redirectStrategy.sendRedirect(request, response, defaultUrl);
	}
	
	/**
	 * 인증 성공후 어떤 URL로 redirect 할지를 결정한다
	 * 판단 기준은 targetUrlParameter 값을 읽은 URL이 존재할 경우 그것을 1순위
	 * 1순위 URL이 없을 경우 Spring Security가 세션에 저장한 URL을 2순위
	 * 2순위 URL이 없을 경우 Request의 REFERER를 사용하고 그 REFERER URL이 존재할 경우 그 URL을 3순위
	 * 3순위 URL이 없을 경우 Default URL을 4순위로 한다
	 * @param request
	 * @param response
	 * @return   1 : targetUrlParameter 값을 읽은 URL
	 *           2 : Session에 저장되어 있는 URL
	 *           3 : referer 헤더에 있는 url
	 *           0 : default url
	 *            
	 *            
	 *            
	 *            우선 순위별 추가 설명.
	 *1. 지정된 Request Parameter(loginRedirect)에 로그인 작업을 마친 뒤 redirect 할 URL을 지정했다면 이 URL로 redirect 하도록 한다.(return 1)
	 *2. 만약 지정된 Request Parameter에 지정된 URL이 없다면 세션에 저장된 URL로 redirect 하도록 한다.(return 2)
	 *3. 세션에 저장된 URL도 없다면 Request의 REFERER 헤더값을 읽어서 로그인 페이지를 방문하기 전 페이지의 URL을 읽어서 거기로 이동하도록 한다.(return 3)
	 *4. 위의 3가지 경우 모두 만족하는게 없으면 CustomAuthenticationSuccessHandler 클래스에 있는 defaultUrl 속성에 지정된 URL로  이동하도록 한다.(return 0)
	 *
	 *
	 *				구현 방법
	 *1. HttpServletRequest 객체를 가져오고 있기 때문에 URL이 들어가 있을 파라미터 이름으로 getParameter(파라미터 이름) 메소드를 실행
	 *2. HttpSessionRequestCache 클래스 객체를 생성해서 거기서 DefaultSavedRequest 클래스 객체를 가져온뒤 거기서 getRedirectURL 메소드를 호출.
	 *		Spring Security가 시스템적으로 로그인 화면을 띄울때는 DefaultSavedRequest 객체를 만들어서 로그인 화면을 보기 전의
	 *		화면에 대한 URL과 헤더 정보들을 저장해놓지만, 사용자가 링크를 타고 로그인 화면으로 이동했거나 직접 URL을 입력하여
	 *		로그인 화면을 이동했을 경우엔 이런 DefaultSavedRequest 객체를 저장하지를 않기 때문에 세션에 저장x.
	 *3. 그런 경우를 대비해서 HttpServletRequest 객체의 getHeader 메소드를 이용해 REFERER 헤더 값을 읽어올 수 있다.
	 *		위에서 이 기능에 대한 얘기를 했을때 이 기능은 사용 여부를 설정 할 수 있게끔 하도록 한다고 했다. form 태그의 action 경로도
	 *		url을 얻어오기 때문에 이쪽으로 이동할 수도 있기 때문.
	 *4. 그냥 디폴트 url            
	 */
	private int decideRedirectStrategy(HttpServletRequest request, HttpServletResponse response){
		int result = 0;
		
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		if(!"".equals(targetUrlParameter)){
			String targetUrl = request.getParameter(targetUrlParameter);
			
			if(StringUtils.hasText(targetUrl)){
				result = 1;
			}else{
				if(savedRequest != null){
					result = 2;
				}else{
					String refererUrl = request.getHeader("REFERER");
					if(useReferer && StringUtils.hasText(refererUrl)){
						result = 3;
					}else{
						result = 0;
					}
				}
			}
			
			return result;
		}
		
		if(savedRequest != null){
			result = 2;
			return result;
		}
		
		String refererUrl = request.getHeader("REFERER");
		if(useReferer && StringUtils.hasText(refererUrl)){
			result = 3;
		}else{
			result = 0;
		}
		
		return result;
	}
}
