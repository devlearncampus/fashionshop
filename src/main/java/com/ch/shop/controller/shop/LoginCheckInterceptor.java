package com.ch.shop.controller.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
로그인 한 회원에게만 제공되는 서비스를 판단하여, 해당 유저가 로그인 하지 않았을 경우, 
로그인 폼을 강제로 보여주는 처리를 위해서는 , 세션 체크코드를 작성해야 한다.
하지만, 회원에게만 제공되는 요청을 처리하는 모든 컨트롤러마다 세션 체크 코드를 넣으면 코드 중복이 발생하므로, 
유지보수성을 위해서는 스프링에서 제공하는 인터셉터를 이용하면 된다  
*/
public class LoginCheckInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		
		if(session ==null || session.getAttribute("member")==null) {			
	        // Ajax 요청인지 확인
	        String ajaxHeader = request.getHeader("X-Requested-With");
	        
	        boolean isAjax = "XMLHttpRequest".equals(ajaxHeader);

	        if (isAjax) {
	            // JSON 응답으로 로그인 필요 알림
	            response.setContentType("application/json; charset=UTF-8");
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            response.getWriter().write("{\"msg\":\"로그인이 필요한 서비스입니다\"}");
	        } else {
	            // 일반 요청이면 기존 리다이렉트
	            response.sendRedirect(request.getContextPath() + "/member/loginform");
	        }
	        return false; // 컨트롤러 진입 차단
		}
		//그렇지 않은 경우 컨트롤러 수행   
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}











