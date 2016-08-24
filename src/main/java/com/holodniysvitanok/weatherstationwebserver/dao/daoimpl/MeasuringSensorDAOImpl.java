package com.holodniysvitanok.weatherstationwebserver.dao.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.holodniysvitanok.weatherstationwebserver.dao.MeasuringSensorDAO;
import com.holodniysvitanok.weatherstationwebserver.entity.MeasuringSensor;

@Repository
public class MeasuringSensorDAOImpl implements MeasuringSensorDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void createMeasuringSensor(MeasuringSensor measuringSensor) {
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	@Transactional
	public List<MeasuringSensor> getAllMeasuringSensors() {
		Session session = sessionFactory.getCurrentSession();
		List<MeasuringSensor> list = session.createCriteria(MeasuringSensor.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
	}

	@Override
	@Transactional
	public MeasuringSensor getOneMeasuringSensorById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from MeasuringSensor where id = :id");
		query.setParameter("id", id);
		return (MeasuringSensor)query.getSingleResult();
	}

	@Override
	@Transactional
	public void deleteMeasuringSensor(MeasuringSensor measuringSensor) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(measuringSensor);
	}

	@Override
	@Transactional
	public void updateMeasuringSensor(MeasuringSensor measuringSensor) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(measuringSensor);
	}

	@Override
	@Transactional
	public List<MeasuringSensor> getAllActiveMeasuringSensors() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from MeasuringSensor where active = true");
		return query.list();

	}

}
