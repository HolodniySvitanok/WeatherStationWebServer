package com.holodniysvitanok.weatherstationwebserver.services.chart;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "point")
@XmlAccessorType(XmlAccessType.FIELD)
public class Point {

    private int y;
    private Date date;

    public void setY(int y) {
        this.y = y;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getY() {
        return y;
    }

    public Date getDate() {
        return date;
    }

    public Point(int y, Date date) {
        this.y = y;
        this.date = date;
    }

    public Point() {
    }

}
