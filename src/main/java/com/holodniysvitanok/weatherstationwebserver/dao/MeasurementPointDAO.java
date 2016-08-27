package com.holodniysvitanok.weatherstationwebserver.dao;

import java.util.Date;
import java.util.List;

import com.holodniysvitanok.weatherstationwebserver.entity.MeasurementPoint;
import com.holodniysvitanok.weatherstationwebserver.entity.MeasurementPoint.TypeMeasurement;
import com.holodniysvitanok.weatherstationwebserver.services.Period;

public interface MeasurementPointDAO {

    void createMeasurementPoint(MeasurementPoint mPoint);

    List<MeasurementPoint> getAllMeasurementPoint();
    
    List<MeasurementPoint> getMeasurementPoint(int count); // =)

    MeasurementPoint getOneMeasurementPointById(long id);

    void deleteMeasurementPoint(MeasurementPoint mPoint);

    void updateMeasurementPoint(MeasurementPoint mPoint);
    
    List<MeasurementPoint> getMeasurementPointInPeriod(Date stDate, Date enDate, MeasurementPoint.TypeMeasurement type, int sensorId);
    
    List<MeasurementPoint> getMeasurementPointInPeriod(Period period);
    
    MeasurementPoint getLastMeasurementPoint(TypeMeasurement typeMeasurement, int sensorId);
    
}
