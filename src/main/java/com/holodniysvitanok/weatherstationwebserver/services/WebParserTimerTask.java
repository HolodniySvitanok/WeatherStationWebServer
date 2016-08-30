package com.holodniysvitanok.weatherstationwebserver.services;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.holodniysvitanok.weatherstationwebserver.dao.MeasurementPointDAO;
import com.holodniysvitanok.weatherstationwebserver.entity.MeasurementPoint;
import com.holodniysvitanok.weatherstationwebserver.entity.MeasurementPoint.TypeMeasurement;
import com.holodniysvitanok.weatherstationwebserver.entity.MeasuringSensor;



public class WebParserTimerTask extends TimerTask {


	
	
	private static final String url = "http://meteopost.com/weather/dnepropetrovsk/";

	private static final String str = "<span class=dat>";
	private static final String deg = "&deg";
	private static final String pr = "%";
	private static final String mmRtSt = "мм.рт.ст";

	private static final String tempWord = "Температура воздуха";
	private static final String humWord = "Влажность";
	private static final String presWord = "Давление (на уровне моря)";
	
	private int idSensor;
	
	private MeasurementPointDAO measurementPointDAO;
	

	@Override
	public void run() {
		
		try {
			URL urlCon = new URL(url);

			BufferedReader bReader = new BufferedReader(new InputStreamReader(urlCon.openStream(), "cp1251"));

			String temp = null;
			StringBuilder sb = new StringBuilder();

			while ((temp = bReader.readLine()) != null) {
				sb.append(temp);
			}

			parse(sb.toString()); 

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void parse(String html) {
				
		int t1 = html.indexOf(tempWord); 
		int h1 = html.indexOf(humWord); 
		int p1 = html.indexOf(presWord); 

		int temp1 = html.indexOf(str, t1);
		int temp2 = html.indexOf(deg, temp1);

		int temperature = Integer.parseInt(html.substring(temp1 + str.length(), temp2));
		
		measurementPointDAO.createMeasurementPoint(
			new MeasurementPoint(temperature*10, new Date(), TypeMeasurement.Temperature, new MeasuringSensor(this.idSensor))
		);
		

		temp1 = html.indexOf(str, h1);
		temp2 = html.indexOf(pr, temp1);

		int humidity = Integer.parseInt(html.substring(temp1 + str.length(), temp2));

		measurementPointDAO.createMeasurementPoint(
			new MeasurementPoint(humidity, new Date(), TypeMeasurement.Humidity, new MeasuringSensor(this.idSensor))
		);
		
		temp1 = html.indexOf(str, p1);
		temp2 = html.indexOf(mmRtSt, temp1);

		int pressure = Integer.parseInt(html.substring(temp1 + str.length(), temp2).trim());
		
		measurementPointDAO.createMeasurementPoint(
			new MeasurementPoint(pressure, new Date(), TypeMeasurement.Pressure, new MeasuringSensor(this.idSensor))
		);
	}

	public int getIdSensor() {
		return this.idSensor;
	}

	public void setIdSensor(int idSensor) {
		this.idSensor = idSensor;
	}

	public WebParserTimerTask(MeasurementPointDAO measurementPointDAO) {
		this.measurementPointDAO = measurementPointDAO;
	}
	
	
}
