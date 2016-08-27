package com.holodniysvitanok.weatherstationwebserver.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.holodniysvitanok.weatherstationwebserver.entity.User;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter{


	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		if(user == null){
			response.sendRedirect(request.getServletContext().getContextPath()+"/authorization");
			return false;
		}
		return super.preHandle(request, response, handler);
	}
	
}
