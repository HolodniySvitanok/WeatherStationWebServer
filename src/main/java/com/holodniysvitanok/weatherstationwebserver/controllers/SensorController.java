package com.holodniysvitanok.weatherstationwebserver.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.holodniysvitanok.weatherstationwebserver.dao.MeasuringSensorDAO;
import com.holodniysvitanok.weatherstationwebserver.entity.MeasuringSensor;

@Controller
public class SensorController {

	@Autowired
	private MeasuringSensorDAO measuringSensorDAO;

	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value = "/hood/sensors", method = RequestMethod.GET)
	public ModelAndView sensors(ModelAndView model) {

		List<MeasuringSensor> allMeasuringSensors = measuringSensorDAO.getAllMeasuringSensors();
		model.addObject("allSensors", allMeasuringSensors);
		model.setViewName("sensors");

		return model;
	}

	@RequestMapping(value = "/hood/setSensor", method = RequestMethod.GET)
	public String setSensor(HttpServletRequest request) {

		String val = request.getParameter("id");
		if (val.equals("add")) {
			return "setSensor";
		}

		int id = Integer.parseInt(val);
		MeasuringSensor sensor = measuringSensorDAO.getOneMeasuringSensorById(id);
		request.setAttribute("sensor", sensor);

		return "setSensor";
	}

	@RequestMapping(value = "/hood/setSensor", method = RequestMethod.POST)
	public void saveOrUpdateSensor(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// НАДО ВАЛИДАЦИЯ
		String location = request.getParameter("location");
		String password = request.getParameter("password");
		boolean active = Boolean.valueOf(request.getParameter("active"));

		String idVal = request.getParameter("id");
		MeasuringSensor sensor = null;

		if (idVal != null) {
			int id = Integer.parseInt(idVal);
			sensor = new MeasuringSensor(id, location, password, active);
		} else {
			sensor = new MeasuringSensor(location, password, active);
		}

		measuringSensorDAO.updateMeasuringSensor(sensor);
		updateContext();

		response.sendRedirect(servletContext.getContextPath() + "/hood/sensors");
	}

	@RequestMapping(value = "/hood/delete", method = RequestMethod.GET)
	public void deleteSensor(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		measuringSensorDAO.deleteMeasuringSensor(new MeasuringSensor(id));

		updateContext();
		response.sendRedirect(servletContext.getContextPath() + "/hood/sensors");
	}

	private void updateContext() {
		List<MeasuringSensor> listSensors = measuringSensorDAO.getAllActiveMeasuringSensors();
		servletContext.setAttribute("listSensors", listSensors);
	}
}
