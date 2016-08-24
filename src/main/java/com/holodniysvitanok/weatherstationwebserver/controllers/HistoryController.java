package com.holodniysvitanok.weatherstationwebserver.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.holodniysvitanok.weatherstationwebserver.dao.MeasurementPointDAO;
import com.holodniysvitanok.weatherstationwebserver.services.GlobalConfig;


@Controller
public class HistoryController {
	
	@Autowired
	private MeasurementPointDAO measurementPointDAO;
	
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public String history(HttpServletRequest request) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(GlobalConfig.DATE_FORMAT);
		Calendar calendar = Calendar.getInstance();
		String dateString = dateFormat.format(calendar.getTime());
		String yearEnd = dateString.substring(0, 2);
		String monthEnd = dateString.substring(2, 4);
		String dayEnd = dateString.substring(4, 6);
		String hourEnd = dateString.substring(6, 8);
		request.setAttribute("yearEnd", yearEnd);
		request.setAttribute("monthEnd", monthEnd);
		request.setAttribute("dayEnd", dayEnd);
		request.setAttribute("hourEnd", hourEnd);
		
		calendar.add(Calendar.HOUR, -24);
		dateString = dateFormat.format(calendar.getTime());

		String yearStart = dateString.substring(0, 2);
		String monthStart = dateString.substring(2, 4);
		String dayStart = dateString.substring(4, 6);
		String hourStart = dateString.substring(6, 8);
		request.setAttribute("yearStart", yearStart);
		request.setAttribute("monthStart", monthStart);
		request.setAttribute("dayStart", dayStart);
		request.setAttribute("hourStart", hourStart);		
		
		return "history";
	}
	
}
