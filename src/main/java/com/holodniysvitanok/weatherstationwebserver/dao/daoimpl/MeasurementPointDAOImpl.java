package com.holodniysvitanok.weatherstationwebserver.dao.daoimpl;

import com.holodniysvitanok.weatherstationwebserver.dao.MeasurementPointDAO;
import com.holodniysvitanok.weatherstationwebserver.entity.MeasurementPoint;
import com.holodniysvitanok.weatherstationwebserver.services.Period;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MeasurementPointDAOImpl implements MeasurementPointDAO {

    @Autowired
    private SessionFactory sessionFactory;
        
    @Override
    @Transactional
    public void createMeasurementPoint(MeasurementPoint mPoint) {
        Session session = sessionFactory.getCurrentSession();
        session.save(mPoint);

    }

    @Override
    @Transactional
    public List<MeasurementPoint> getAllMeasurementPoint() {

        Session session = sessionFactory.getCurrentSession();
        List<MeasurementPoint> list
                = session.createCriteria(MeasurementPoint.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        return list;
    }

    @Override
    public void getOneMeasurementPointById(int id) {
    }

    @Override
    public void deleteMeasurementPoint(MeasurementPoint mPoint) {
    }

    @Override
    public void updateMeasurementPoint(MeasurementPoint mPoint) {

    }

    @Override
    @Transactional
    public List<MeasurementPoint> getMeasurementPointInPeriod(Date stDate, Date enDate, MeasurementPoint.TypeMeasurement type, int sensorId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from MeasurementPoint where id_measuring_sensor = :sensorId and datePoint between :startDate and :endDate ");
        query.setDate("startDate", stDate);
        query.setDate("endDate", enDate);
        query.setParameter("sensorId", sensorId);
        return query.list();
    }

    @Override
    @Transactional
    public List<MeasurementPoint> getMeasurementPointInPeriod(Period period) {
        return getMeasurementPointInPeriod(period.getStDate(), period.getEndDate(), period.getType(), period.getSensorId());
    }

}