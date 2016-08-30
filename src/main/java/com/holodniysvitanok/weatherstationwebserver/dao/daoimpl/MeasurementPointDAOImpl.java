package com.holodniysvitanok.weatherstationwebserver.dao.daoimpl;

import com.holodniysvitanok.weatherstationwebserver.dao.MeasurementPointDAO;
import com.holodniysvitanok.weatherstationwebserver.entity.MeasurementPoint;
import com.holodniysvitanok.weatherstationwebserver.entity.MeasurementPoint.TypeMeasurement;
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
		List<MeasurementPoint> list = session.createCriteria(MeasurementPoint.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
	}

	@Override
	@Transactional
	public MeasurementPoint getOneMeasurementPointById(long id) {

		Session session = sessionFactory.getCurrentSession();
		MeasurementPoint point = session.get(MeasurementPoint.class, id);
		return point;
		
	}

	@Override
	@Transactional
	public void deleteMeasurementPoint(MeasurementPoint mPoint) {
		Session session = sessionFactory.getCurrentSession();
		session.remove(mPoint);
	}

	@Override
	@Transactional
	public void updateMeasurementPoint(MeasurementPoint mPoint) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(mPoint);
	}

	@Override
	@Transactional
	public List<MeasurementPoint> getMeasurementPointInPeriod(Date stDate, Date enDate,
			MeasurementPoint.TypeMeasurement type, int sensorId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"from MeasurementPoint where id_measuring_sensor = :sensorId and typeMeasurement = :type and datePoint between :startDate and :endDate order by date_measurement_point asc ");
		query.setTimestamp("startDate", stDate);	
		query.setTimestamp("endDate", enDate);
		query.setParameter("type", type);
		query.setParameter("sensorId", sensorId);
		return query.list();
	}

	@Override
	@Transactional
	public List<MeasurementPoint> getMeasurementPointInPeriod(Period period) {
		return getMeasurementPointInPeriod(period.getStDate(), period.getEndDate(), period.getType(),
				period.getSensorId());
	}

	@Override
	@Transactional
	public MeasurementPoint getLastMeasurementPoint(TypeMeasurement typeMeasurement, int sensorId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from MeasurementPoint where typeMeasurement = :typeM and id_measuring_sensor = :sensorId "
						+ "order by date_measurement_point desc");

		query.setParameter("typeM", typeMeasurement);
		query.setParameter("sensorId", sensorId);
		query.setMaxResults(1);
		return (MeasurementPoint) query.uniqueResult();
	}

	@Override
	@Transactional
	public List<MeasurementPoint> getMeasurementPoint(int count) {
		Session session = sessionFactory.getCurrentSession();
		List<MeasurementPoint> list = session.createCriteria(MeasurementPoint.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).setMaxResults(count).list();
		return list;
	}



}
