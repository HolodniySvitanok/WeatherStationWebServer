package com.holodniysvitanok.weatherstationwebserver.dao;

import java.util.List;

import com.holodniysvitanok.weatherstationwebserver.entity.User;

public interface UserDAO {

	void createUser(User user);

	List<User> getAllUser();

	User getOneUserById(int id);
	
	User authorizationUser(User user);

	void deleteUser(int id);

	void saveOrUpdateUser(User user);

}
