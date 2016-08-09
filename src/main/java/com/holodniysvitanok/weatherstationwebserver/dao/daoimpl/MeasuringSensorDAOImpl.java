package com.holodniysvitanok.weatherstationwebserver.dao.daoimpl;

import java.util.List;
//import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import com.holodniysvitanok.weatherstationwebserver.dao.MeasuringSensorDAO;
import com.holodniysvitanok.weatherstationwebserver.entity.MeasuringSensor;

public class MeasuringSensorDAOImpl implements MeasuringSensorDAO{

    private SessionFactory sessionFactory;
 
    // DI через конструктор
    public MeasuringSensorDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
//    @Transactional
    public void createMeasuringSensor(MeasuringSensor measuringSensor) {
    }

    @Override
//    @Transactional
    public List<MeasuringSensor> getAllMeasuringSensors() {
        return null;
    }

    @Override
//    @Transactional
    public MeasuringSensor getOneMeasuringSensorById(int id) {
        return null;
    }

    @Override
//    @Transactional
    public void deleteMeasuringSensor(MeasuringSensor measuringSensor) {
    }

    @Override
//    @Transactional
    public void updateMeasuringSensor(MeasuringSensor measuringSensor) {
    }

}
