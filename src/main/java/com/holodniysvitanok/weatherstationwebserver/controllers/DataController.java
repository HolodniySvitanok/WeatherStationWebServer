package com.holodniysvitanok.weatherstationwebserver.controllers;

import com.holodniysvitanok.weatherstationwebserver.dao.MeasurementPointDAO;
import com.holodniysvitanok.weatherstationwebserver.entity.MeasurementPoint;
import com.holodniysvitanok.weatherstationwebserver.services.GlobalConfig;
import com.holodniysvitanok.weatherstationwebserver.services.Period;
import com.holodniysvitanok.weatherstationwebserver.services.PointsConverter;
import com.holodniysvitanok.weatherstationwebserver.services.chart.Chart;
import com.holodniysvitanok.weatherstationwebserver.services.chart.Pointers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	private MeasurementPointDAO measurementPointDAO; // связь с бд

	
	
	
	// возвращает в поток xml c точками (для swing клиента)
	@RequestMapping(value = "/getxml", method = RequestMethod.GET)
	public @ResponseBody Pointers xmlToStream(HttpServletRequest request, HttpServletResponse response) {

		Pointers pointers = null;
		response.setContentType("text/xml");

		try {
			Period period = Utility.createPeriod(request.getParameterMap());
			List<MeasurementPoint> list = measurementPointDAO.getMeasurementPointInPeriod(period);

			pointers = new PointsConverter().pointMeasurementToPointChart(list, true);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ошибка при отправки xml");
		}
		return pointers;
	}

	
	
	
	// возвращает поток с изображением
	@RequestMapping(value = "/getimage", method = RequestMethod.GET)
	public void imageToStream(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("image/png");
		ServletOutputStream outputStream = null;


		try {
			Period period = Utility.createPeriod(request.getParameterMap());
			List<MeasurementPoint> list = measurementPointDAO.getMeasurementPointInPeriod(period);

			if (list.isEmpty()) {
				request.setAttribute("image", false);
				return;
			}
			request.setAttribute("image", true);
			outputStream = response.getOutputStream();
			PointsConverter pc = new PointsConverter();
			
			new Chart().createImage(pc.pointMeasurementToPointChart(list, true), outputStream);

		} catch (RuntimeException | IOException | ParseException ex) {
			System.out.println("ошибка createImageChart()"); // TODO ЗАМЕНИТЬ НА
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

	
	
	
	// TODO заменить
	private static class Utility {

		final static String START_DATE = "sdate";
		final static String END_DATE = "edate";
		final static String TYPE = "type";
		final static String SENSOR_ID = "id";

		static Period createPeriod(Map<String, String[]> requestParameters) throws ParseException {
			
			SimpleDateFormat sdf = new SimpleDateFormat(GlobalConfig.DATE_FORMAT);
			Period period = new Period();

			period.setStDate(sdf.parse(requestParameters.get(START_DATE)[0]));
			period.setEndDate(sdf.parse(requestParameters.get(END_DATE)[0]));
			period.setSensorId(Integer.parseInt(requestParameters.get(SENSOR_ID)[0]));
			period.setType(MeasurementPoint.TypeMeasurement.fromString(requestParameters.get(TYPE)[0]));

			return period;
		}

	}
}
