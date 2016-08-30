package com.holodniysvitanok.weatherstationwebserver.services;

import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.holodniysvitanok.weatherstationwebserver.dao.MeasurementPointDAO;


@Component
public class Parse {
	
	private WebParserTimerTask webParser;
	
	@Autowired
	private MeasurementPointDAO measurementPointDAO;
	
	
	private Timer timer;
	private boolean active;
	
	
	public boolean isActive() {
		return active;
	}


	public void parserManagement(int idSensor, String confWord) {

		if (confWord.equalsIgnoreCase("on")) {
			timer = new Timer(true);
			
			webParser = new WebParserTimerTask(measurementPointDAO);
			webParser.setIdSensor(idSensor);
			timer.scheduleAtFixedRate(webParser, 0, 1800 * 1000);
			this.active = true;
			
		}

		if (confWord.equalsIgnoreCase("off")) {
			
			timer.cancel();
			this.active = false;
			
		}

	}
}
