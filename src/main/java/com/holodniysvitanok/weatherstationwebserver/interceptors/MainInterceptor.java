package com.holodniysvitanok.weatherstationwebserver.interceptors;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.holodniysvitanok.weatherstationwebserver.dao.MeasuringSensorDAO;
import com.holodniysvitanok.weatherstationwebserver.entity.MeasuringSensor;

public class MainInterceptor extends HandlerInterceptorAdapter {



	@Autowired
	private MeasuringSensorDAO measuringSensorDAO;

	@Autowired
	private ServletContext servletContext;

	
	/*
	 * Инициализируем перехватчик Добовляем список датчиков в контекст
	 */
	@PostConstruct
	public void init() {

		List<MeasuringSensor> listSensors = measuringSensorDAO.getAllActiveMeasuringSensors();
		servletContext.setAttribute("listSensors", listSensors);

	}

	/*
	 * 
	 * 
	 * 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("location") == null) {
			findCookia(request, response);
		}
		return super.preHandle(request, response, handler);
	}

	/*
	 * 
	 * находим нужную куку и её значение записываем в сессию
	 * 
	 */
	private void findCookia(HttpServletRequest request, HttpServletResponse response) {

		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("location")) {
					request.getSession(true).setAttribute("location", Integer.parseInt(cookies[i].getValue()));
					return;
				}
			}
		}

		List<MeasuringSensor> listSensors = (List<MeasuringSensor>) servletContext.getAttribute("listSensors");

		for (MeasuringSensor sensor : listSensors) {
			if (sensor.isActive()) {
				Cookie cookie = new Cookie("location", String.valueOf(sensor.getId()));
				cookie.setMaxAge(1000 * 60 * 60 * 31);
				response.addCookie(cookie);
				request.getSession(true).setAttribute("location", sensor.getId());
				break;
			}
		}
	}

}
