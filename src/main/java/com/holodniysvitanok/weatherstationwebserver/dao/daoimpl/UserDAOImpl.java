package com.holodniysvitanok.weatherstationwebserver.dao.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.holodniysvitanok.weatherstationwebserver.dao.UserDAO;
import com.holodniysvitanok.weatherstationwebserver.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void createUser(User user) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(user);
	}


	@Override
	@Transactional
	public List<User> getAllUser() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM  User");
		return query.list();
	}

	@Override
	@Transactional
	public User getOneUserById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		User user = (User) currentSession.get(User.class, id);
		return user;
	}

	@Override
	@Transactional
	public void deleteUser(int id) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, id);
		session.delete(user);

	}

	@Override
	@Transactional
	public void saveOrUpdateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}


	@Override
	@Transactional
	public User authorizationUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM  User where login = :lgn and password = :pswd");
		query.setParameter("lgn", user.getLogin());
		query.setParameter("pswd", user.getPassword());
		
		 
		return (User) query.getSingleResult();
	}

}
