package com.holodniysvitanok.weatherstationwebserver.controllers;

import com.holodniysvitanok.weatherstationwebserver.dao.MeasurementPointDAO;
import com.holodniysvitanok.weatherstationwebserver.dao.MeasuringSensorDAO;
import com.holodniysvitanok.weatherstationwebserver.entity.MeasurementPoint;
import com.holodniysvitanok.weatherstationwebserver.entity.MeasuringSensor;
import com.holodniysvitanok.weatherstationwebserver.services.GlobalConfig;
import com.holodniysvitanok.weatherstationwebserver.services.Period;
import com.holodniysvitanok.weatherstationwebserver.services.PointsConverter;
import com.holodniysvitanok.weatherstationwebserver.services.chart.Chart;
import com.holodniysvitanok.weatherstationwebserver.services.chart.ChartPointers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DataController {

	
	@Autowired
	private MeasurementPointDAO measurementPointDAO; 

	@Autowired
	private MeasuringSensorDAO measuringSensorDAO;
	
	
	/*
	 * 
	 * возвращает в поток xml c точками (для swing клиента)
	 * 
	 * */ 
	@RequestMapping(value = "/getxml", method = RequestMethod.GET)
	public @ResponseBody ChartPointers xmlToStream(HttpServletRequest request, HttpServletResponse response) {

		ChartPointers pointers = null;
		response.setContentType("text/xml");

		try {
			Period period = Utility.createPeriod(request.getParameterMap());
			List<MeasurementPoint> list = measurementPointDAO.getMeasurementPointInPeriod(period);

			pointers = new PointsConverter().pointMeasurementToPointChart(list);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ошибка при отправки xml");
		}
		return pointers;
	}

	
	/*
	 * 
	 * возвращает поток с изображением
	 * 
	 * */ 
	@RequestMapping(value = "/getimage", method = RequestMethod.GET)
	public void imageToStream(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("image/png");
		ServletOutputStream outputStream = null;


		try {
			Period period = Utility.createPeriod(request.getParameterMap());
			List<MeasurementPoint> list = measurementPointDAO.getMeasurementPointInPeriod(period);

			if (list.isEmpty()) {
				return;
			}
			outputStream = response.getOutputStream();
			
			PointsConverter pc = new PointsConverter();
			
			ChartPointers pointMeasurementToPointChart = pc.pointMeasurementToPointChart(list);
			
			Chart chart = new Chart();
			chart.createImage(pointMeasurementToPointChart, outputStream);

		} catch (RuntimeException | IOException | ParseException ex) {
			System.out.println("ошибка createImageChart()"); // TODO ЗАМЕНИТЬ
			ex.printStackTrace();
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (Exception e) {
				System.out.println("ошибка при закр");
			}
		}
	}

	/*
	 * 
	 * Добавление точек с датчика
	 * 
	 * 
	 * */
	@RequestMapping(value = "/addPoint", method = RequestMethod.POST)
	public void addPoint(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String idStr = request.getParameter("a1"); 				// ид
		String passwordStr = request.getParameter("a2"); 		// пароль
		
		MeasuringSensor oneMeasuringSensorById = measuringSensorDAO.getOneMeasuringSensorById(Integer.parseInt(idStr));
		
		if(!oneMeasuringSensorById.getPassword().equals(passwordStr)){
			return;
		}
		
		String parameterValue = request.getParameter("val");
		String parameterDate = request.getParameter("date"); // ИСПОЛЬЗОВАТЬ
		
		MeasurementPoint measurementPoint = new MeasurementPoint();
		measurementPoint.setValue(Integer.parseInt(parameterValue));
		measurementPoint.setDatePoint(new Date()); // TODO ЗАМЕНИТЬ
		measurementPoint.setMeasuringSensor(new MeasuringSensor(Integer.parseInt(idStr)));
		
		measurementPointDAO.createMeasurementPoint(measurementPoint);
		
		ServletOutputStream outputStream = response.getOutputStream();
		
		byte [] answer = {1};          // TODO надо подумать ....
		outputStream.write(answer);
		
		outputStream.close();
	}
	
	
	
	
	/*
	 * 
	 * Класс утилита для внешнего класса
	 * 
	 * */
	private static class Utility {

		final static String START_DATE = "sdate";
		final static String END_DATE = "edate";
		final static String TYPE = "type";
		final static String SENSOR_ID = "id";

		static Period createPeriod(Map<String, String[]> requestParameters) throws ParseException {
			
			SimpleDateFormat sdf = new SimpleDateFormat(GlobalConfig.DATE_FORMAT_URL);
			Period period = new Period();

			period.setStDate(sdf.parse(requestParameters.get(START_DATE)[0]));
			period.setEndDate(sdf.parse(requestParameters.get(END_DATE)[0]));
			period.setSensorId(Integer.parseInt(requestParameters.get(SENSOR_ID)[0]));
			period.setType(MeasurementPoint.TypeMeasurement.fromString(requestParameters.get(TYPE)[0]));

			return period;
		}

	}
}
