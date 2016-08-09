package com.holodniysvitanok.weatherstationwebserver.services;

import java.util.Date;

import com.holodniysvitanok.weatherstationwebserver.entity.MeasurementPoint;


// класс инкапсулирующий параметры для запроса 
public class Period {

    private Date stDate;
    private Date endDate;
    private MeasurementPoint.TypeMeasurement type;
    private int sensorId; 

    public Period() {
    }
    
    public Period(Date stDate, Date endDate, MeasurementPoint.TypeMeasurement type, int sensorId) {
        this.stDate = stDate;
        this.endDate = endDate;
        this.type = type;
        this.sensorId = sensorId;
    }

    public Date getStDate() {
        return stDate;
    }

    public void setStDate(Date stDate) {
        this.stDate = stDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public MeasurementPoint.TypeMeasurement getType() {
        return type;
    }

    public void setType(MeasurementPoint.TypeMeasurement type) {
        this.type = type;
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    @Override
    public String toString() {
        return "Period{" + "stDate=" + stDate + ", endDate=" + endDate + ", type=" + type + ", sensorId=" + sensorId + '}';
    }

    
    
}
