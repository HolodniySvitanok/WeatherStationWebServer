package com.holodniysvitanok.weatherstationwebserver.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.holodniysvitanok.weatherstationwebserver.services.GlobalConfig;


@Controller
public class HistoryController {
	
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * */
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public String history(HttpServletRequest request) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(GlobalConfig.DATE_FORMAT);
		Calendar calendar = Calendar.getInstance();
		String end = dateFormat.format(calendar.getTime());
		
		request.setAttribute("end", end);
		calendar.add(Calendar.HOUR, -12);
		
		String start = dateFormat.format(calendar.getTime());
		
		request.setAttribute("start", start);
		request.setAttribute("show", false);
		return "history";
	}
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * */
	@RequestMapping(value = "/history", method = RequestMethod.POST)
	public String showHistory(HttpServletRequest request) throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(GlobalConfig.DATE_FORMAT);
		
		String startStr = request.getParameter("start");
		String endStr = request.getParameter("end");
		request.setAttribute("start", startStr);
		request.setAttribute("end", endStr);
				
		String locSel = request.getParameter("loc_sel");
		
		if(locSel.equals("-")){
			request.setAttribute("idSubLoc", -1);
		}else{
			request.setAttribute("idSubLoc", Integer.parseInt(locSel));
		}

		Date tempStart = dateFormat.parse(startStr);
		Date tempEnd = dateFormat.parse(endStr);
		
		dateFormat = new SimpleDateFormat(GlobalConfig.DATE_FORMAT_URL);
				
		String start = dateFormat.format(tempStart);
		String end = dateFormat.format(tempEnd);
		
		request.setAttribute("startDate", start);
		request.setAttribute("endDate", end);
		request.setAttribute("id", request.getSession().getAttribute("location"));
		request.setAttribute("show", true);
		
		return "history";
	}
	
}
