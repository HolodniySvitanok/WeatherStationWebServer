package com.holodniysvitanok.weatherstationwebserver.controllers;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.holodniysvitanok.weatherstationwebserver.services.GlobalConfig;


@Controller
public class AdminController {
	
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping(value = "/"+GlobalConfig.HOOD_LINK+"/", method = RequestMethod.GET)
	public void adminPage(HttpServletResponse response) throws IOException {
		response.sendRedirect(servletContext.getContextPath()+"/"+GlobalConfig.HOOD_LINK+"/sensors");
	}

}
