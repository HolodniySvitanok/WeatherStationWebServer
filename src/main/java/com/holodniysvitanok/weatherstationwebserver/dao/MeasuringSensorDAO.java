package com.holodniysvitanok.weatherstationwebserver.dao;

import java.util.List;

import com.holodniysvitanok.weatherstationwebserver.entity.MeasuringSensor;

public interface MeasuringSensorDAO {

    void createMeasuringSensor(MeasuringSensor measuringSensor);

    List<MeasuringSensor> getAllMeasuringSensors();

    MeasuringSensor getOneMeasuringSensorById(int id);

    void deleteMeasuringSensor(MeasuringSensor measuringSensor);

    void updateMeasuringSensor(MeasuringSensor measuringSensor);
}
