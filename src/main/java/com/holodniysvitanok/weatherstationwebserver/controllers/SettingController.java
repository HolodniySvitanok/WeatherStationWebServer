package com.holodniysvitanok.weatherstationwebserver.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.holodniysvitanok.weatherstationwebserver.services.GlobalConfig;
import com.holodniysvitanok.weatherstationwebserver.services.Parse;

@Controller
public class SettingController {

	@Autowired
	private Parse parse;

	private int idSensor;
	
	
	@RequestMapping(value = "/" + GlobalConfig.HOOD_LINK + "/setting", method = RequestMethod.GET)
	public String setting(HttpServletRequest request) {
		
		if(request.getParameter("sensorId") != null){
			idSensor = Integer.parseInt(request.getParameter("sensorId"));
		}
		
		
		
		
		if (request.getParameter("v1") != null) {
			parse.parserManagement(idSensor, request.getParameter("v1"));
		}
		
		request.setAttribute("active", parse.isActive());
		request.setAttribute("sensorId", idSensor);
		
		return "setting";
	}


	
}
