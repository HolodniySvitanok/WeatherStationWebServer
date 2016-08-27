package com.holodniysvitanok.weatherstationwebserver.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.holodniysvitanok.weatherstationwebserver.dao.MeasurementPointDAO;
import com.holodniysvitanok.weatherstationwebserver.entity.MeasurementPoint;
import com.holodniysvitanok.weatherstationwebserver.entity.MeasurementPoint.TypeMeasurement;
import com.holodniysvitanok.weatherstationwebserver.entity.MeasuringSensor;
import com.holodniysvitanok.weatherstationwebserver.services.GlobalConfig;

@Controller
public class PointController {
	
	@Autowired
	private ServletContext servletContext;

	@Autowired
	private MeasurementPointDAO measurementPointDAO;

	
	
	
	
	
	
	@RequestMapping(value = "/"+GlobalConfig.HOOD_LINK+"/pointers", method = RequestMethod.GET)
	public String pointers(HttpServletRequest request) {

		int count = 50;
		String str = request.getParameter("count");

		if (str != null) {
			count = Integer.parseInt(str);
		}

		List<MeasurementPoint> measurementPoint = measurementPointDAO.getMeasurementPoint(count);

		request.setAttribute("pointers", measurementPoint);

		return "pointers";
	}

	
	
	
	
	
	
	
	@RequestMapping(value = "/"+GlobalConfig.HOOD_LINK+"/deletePoint", method = RequestMethod.GET)
	public void deletePoint(HttpServletRequest request, HttpServletResponse response) throws IOException {

		long id = Long.parseLong(request.getParameter("id"));
		measurementPointDAO.deleteMeasurementPoint(new MeasurementPoint(id));

		response.sendRedirect(servletContext.getContextPath() + "/"+GlobalConfig.HOOD_LINK+"/pointers");
	}

	
	
	
	
	
	
	
	@RequestMapping(value = "/"+GlobalConfig.HOOD_LINK+"/setPoint", method = RequestMethod.GET)
	public String setPoint(HttpServletRequest request, HttpServletResponse response){
		
		String idVal = request.getParameter("id");
		
		TypeMeasurement[] values = TypeMeasurement.values();
		request.setAttribute("types", values);
		
		if(idVal != null && idVal.equals("add")){
			SimpleDateFormat format = new SimpleDateFormat(GlobalConfig.DATE_FORMAT_POINT);
			request.setAttribute("date", format.format(new Date()));
			return "setPoint";
		}
		
		long id = Long.parseLong(idVal);
		
		MeasurementPoint measurementPoint = measurementPointDAO.getOneMeasurementPointById(id);
		
		request.setAttribute("point", measurementPoint);
		request.setAttribute("date", measurementPoint.getDatePoint());
				
		return "setPoint";
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/"+GlobalConfig.HOOD_LINK+"/setPoint", method = RequestMethod.POST)
	public void saveOrUpdatePoint(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String valId = request.getParameter("id");
		String valDate = request.getParameter("data");
		String valValue = request.getParameter("value");
		String valIdSensor = request.getParameter("idSensor");
		String valT = request.getParameter("ty");
	
		MeasurementPoint measurementPoint = new MeasurementPoint();
		SimpleDateFormat format = new SimpleDateFormat(GlobalConfig.DATE_FORMAT_POINT);
		
		if(valId != null){
			long id = Long.parseLong(valId);
			measurementPoint.setId(id);
		}
		
		try {
			measurementPoint.setDatePoint(format.parse(valDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		measurementPoint.setValue(Integer.parseInt(valValue));
		measurementPoint.setTypeMeasurement(TypeMeasurement.fromString(valT));
		measurementPoint.setMeasuringSensor(new MeasuringSensor(Integer.parseInt(valIdSensor)));
		
		measurementPointDAO.updateMeasurementPoint(measurementPoint);
		
		response.sendRedirect(servletContext.getContextPath()+"/"+GlobalConfig.HOOD_LINK+"/pointers");
	}
	
}
