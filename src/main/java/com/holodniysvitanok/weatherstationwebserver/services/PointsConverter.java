package com.holodniysvitanok.weatherstationwebserver.services;

import com.holodniysvitanok.weatherstationwebserver.entity.MeasurementPoint;
import com.holodniysvitanok.weatherstationwebserver.services.chart.Point;
import com.holodniysvitanok.weatherstationwebserver.services.chart.ChartPointers;
import java.text.SimpleDateFormat;
import java.util.List;

// преобразование набора точек измерения в точки для построения графика
public class PointsConverter {

	private String xName;
	private String yName;
	private String graphicName;

	public PointsConverter(String xName, String yName, String graphicName) {
		this.xName = xName;
		this.yName = yName;
		this.graphicName = graphicName;
	}

	public PointsConverter() {
		this.graphicName = "График";
		this.yName = "Y";
		this.xName = "Время";
	}

	/*
	 * 
	 * 
	 * */
	public ChartPointers pointMeasurementToPointChart(List<MeasurementPoint> measurementPoints) {

		nameChart(measurementPoints);

		ChartPointers pointers = new ChartPointers(xName, yName, graphicName);

		for (MeasurementPoint measurementPoint : measurementPoints) {

			Point point = new Point();

			switch (measurementPoint.getTypeMeasurement()) {
			case Temperature: {
				point.setY((double) measurementPoint.getValue() / 10);
			}
				break;
			case Humidity: {
				point.setY(measurementPoint.getValue());
			}
				break;
			case Pressure: {
				point.setY(measurementPoint.getValue());
				break;
			}

			}
			point.setDate(measurementPoint.getDatePoint());
			pointers.addGraphicsPoint(point);
		}
		return pointers;

	}

	private void nameChart(List<MeasurementPoint> measurementPoints) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		this.graphicName = "в период с " + 
			sdf.format(measurementPoints.get(0).getDatePoint())	+ 
			" до " + 
			sdf.format(measurementPoints.get(measurementPoints.size() - 1).getDatePoint());

		switch (measurementPoints.get(0).getTypeMeasurement()) {

		case Temperature: {
			this.yName = "Температура";
		}
			break;
		case Humidity: {
			this.yName = "Влажность";
		}
			break;
		case Pressure: {
			this.yName = "Давление";
			break;
		}
		default:
			this.yName = "Y";
		}

	}

	public String getxName() {
		return xName;
	}

	public void setxName(String xName) {
		this.xName = xName;
	}

	public String getyName() {
		return yName;
	}

	public void setyName(String yName) {
		this.yName = yName;
	}

	public String getGraphicName() {
		return graphicName;
	}

	public void setGraphicName(String graphicName) {
		this.graphicName = graphicName;
	}
}
