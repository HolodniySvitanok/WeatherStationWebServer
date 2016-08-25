package com.holodniysvitanok.weatherstationwebserver.interceptors;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.holodniysvitanok.weatherstationwebserver.dao.MeasurementPointDAO;
import com.holodniysvitanok.weatherstationwebserver.dao.MeasuringSensorDAO;
import com.holodniysvitanok.weatherstationwebserver.entity.MeasuringSensor;

public class MainInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private MeasurementPointDAO measurementPointDAO;

	@Autowired
	private MeasuringSensorDAO measuringSensorDAO;

	@Autowired
	private ServletContext servletContext;
	
	
	/* Инициализируем перехватчик
	 * Добовляем список датчиков в контекст
	 */
	@PostConstruct
	public void init() {
		
		List<MeasuringSensor> listSensors = measuringSensorDAO.getAllActiveMeasuringSensors();
		servletContext.setAttribute("listSensors", listSensors);
		
	}
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		getLocation(request, response);
		
		return super.preHandle(request, response, handler);
	}

	
	


	
	
	private void getLocation(HttpServletRequest request, HttpServletResponse response) {

		// Если есть сессия и есть аттрибут location то нет смысла дальше продолжать работу перехватчика
		HttpSession session = request.getSession(false);
		
		if (session != null && session.getAttribute("location") != null) {
			request.setAttribute("location", session.getAttribute("location"));
			return;
		}

		// Если нет сессий, лезем в куки ... Если они есть, то берем аттрибут location и выходим
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {

			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("location")) {
					String valValue = cookies[i].getValue();
					request.getSession(true).setAttribute("location", valValue);
					return;
				}
			}
		}
		
		// Если все пусто, то делаем куку и запихиваем в неё перывй активный датчик (список датчиков берем из контекста)
		List<MeasuringSensor> listSensors = (List<MeasuringSensor>) servletContext.getAttribute("listSensors");
		
		for(int i=0; i<listSensors.size(); i++){
			if(listSensors.get(i).isActive() == true){
				Cookie cookie = new Cookie("location", String.valueOf(listSensors.get(i).getId()));
				cookie.setMaxAge(1000 * 60 * 60 * 31);
				response.addCookie(cookie);
			}
			
		}
	}

}
