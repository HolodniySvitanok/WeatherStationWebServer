package com.holodniysvitanok.weatherstationwebserver.services.chart;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ChartPointers")
@XmlAccessorType(XmlAccessType.FIELD)
public class ChartPointers {

	@XmlElement(name = "Point")
	private List<Point> listPointers; // набор точек для графика
	private String xName; // название оси X
	private String yName; // название оси Y
	private String graphicName; // название графика

	public ChartPointers(String xName, String yName, String graphicName) {
		this.listPointers = new ArrayList<>();
		this.xName = xName;
		this.yName = yName;
		this.graphicName = graphicName;
	}

	public ChartPointers() {
	}

	public String getxName() {
		return xName;
	}

	public String getyName() {
		return yName;
	}

	public String getGraphicName() {
		return graphicName;
	}

	public void addGraphicsPoint(Point gPoint) {
		listPointers.add(gPoint);
	}

	public List<Point> getListPointers() {
		return listPointers;
	}

	public void setListPointers(List<Point> listPointers) {
		this.listPointers = listPointers;
	}

	public void setxName(String xName) {
		this.xName = xName;
	}

	public void setyName(String yName) {
		this.yName = yName;
	}

	public void setGraphicName(String graphicName) {
		this.graphicName = graphicName;
	}

}
