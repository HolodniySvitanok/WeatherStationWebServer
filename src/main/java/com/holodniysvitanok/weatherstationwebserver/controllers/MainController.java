package com.holodniysvitanok.weatherstationwebserver.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.holodniysvitanok.weatherstationwebserver.dao.MeasurementPointDAO;
import com.holodniysvitanok.weatherstationwebserver.entity.MeasurementPoint;
import com.holodniysvitanok.weatherstationwebserver.entity.MeasurementPoint.TypeMeasurement;
import com.holodniysvitanok.weatherstationwebserver.services.GlobalConfig;

@Controller
public class MainController {

	
	
	@Autowired
	private MeasurementPointDAO measurementPointDAO;

	/*
	 * 
	 * 
	 * 
	 * */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(ModelAndView model, HttpServletRequest request) {

		int location = (int) request.getSession(false).getAttribute("location");

		MeasurementPoint temperature = measurementPointDAO.getLastMeasurementPoint(TypeMeasurement.Temperature, location);
		MeasurementPoint pressure = measurementPointDAO.getLastMeasurementPoint(TypeMeasurement.Pressure, location);
		MeasurementPoint humidity = measurementPointDAO.getLastMeasurementPoint(TypeMeasurement.Humidity, location);

		buildLink(request);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		if (temperature != null) {
			model.addObject("temperature", (float)temperature.getValue()/10);
		}
		
		if (pressure != null) {
			model.addObject("pressure", pressure.getValue());
		}
		
		if (humidity != null) {
			model.addObject("humidity", humidity.getValue());
		}

		if(temperature != null){
			model.addObject("time", format.format(temperature.getDatePoint()));
		}
		
		

		model.setViewName("home");
		return model;
	}

	/*
	 * 
	 * ВЫБОР ДАТЧИКА. Выбранный датчик сохраняется в сессии для данного пользователя и в куках (проверить что быстрее)
	 * 
	 * 
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void setLocation(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String location = request.getParameter("sel");
		int loc = Integer.parseInt(location);

		request.getSession().setAttribute("location", loc);
		Cookie cookie = new Cookie("location", location);
		cookie.setMaxAge(1000 * 60 * 60 * 31);
		response.addCookie(cookie);
		response.sendRedirect(request.getHeader("Referer"));
	}

	/*
	 * 
	 * 
	 * 
	 * */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public String download() {
		return "download";
	}

	/*
	 * 
	 * 
	 * 
	 * */
	@RequestMapping(value = "/authors", method = RequestMethod.GET)
	public String authors() {
		return "authors";
	}

	/*
	 * 
	 * 
	 * */
	@RequestMapping(value = "/authorization", method = RequestMethod.GET)
	public String authorization() {
		return "authorization";
	}

	/*
	 * 
	 * 
	 * 
	 * */
	private void buildLink(HttpServletRequest request) {
		SimpleDateFormat format = new SimpleDateFormat(GlobalConfig.DATE_FORMAT_URL);
		Calendar calendar = Calendar.getInstance();
		request.setAttribute("edate", format.format(calendar.getTime()));
		calendar.add(Calendar.HOUR, -24);
		request.setAttribute("sdate", format.format(calendar.getTime()));
	}
}
