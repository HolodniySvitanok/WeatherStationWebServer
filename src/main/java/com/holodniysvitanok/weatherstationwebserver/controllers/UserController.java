package com.holodniysvitanok.weatherstationwebserver.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.holodniysvitanok.weatherstationwebserver.dao.UserDAO;
import com.holodniysvitanok.weatherstationwebserver.entity.User;
import com.holodniysvitanok.weatherstationwebserver.services.GlobalConfig;

@Controller
public class UserController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value = "/"+GlobalConfig.HOOD_LINK+"/users", method = RequestMethod.GET)
	public String users(HttpServletRequest request) throws IOException {
		List<User> allUser = userDAO.getAllUser();
		request.setAttribute("users", allUser);
		return "users";
	}

	@RequestMapping(value = "/"+GlobalConfig.HOOD_LINK+"/setUser", method = RequestMethod.GET)
	public String setUser(HttpServletRequest request) {

		String val = request.getParameter("id");
		if (val.equals("add")) {
			return "setUser";
		}

		int id = Integer.parseInt(val);
		User user = userDAO.getOneUserById(id);
		request.setAttribute("user", user);

		return "setUser";
	}

	@RequestMapping(value = "/"+GlobalConfig.HOOD_LINK+"/setUser", method = RequestMethod.POST)
	public void saveOrUpdateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String idVal = request.getParameter("id");
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		User user = null;

		if (idVal != null) {
			int id = Integer.parseInt(idVal);
			user = new User(id, login, password);
		} else {
			user = new User(login, password);
		}

		userDAO.saveOrUpdateUser(user);

		response.sendRedirect(servletContext.getContextPath() + "/"+GlobalConfig.HOOD_LINK+"/users");
	}
	
	@RequestMapping(value = "/"+GlobalConfig.HOOD_LINK+"/deleteUser", method = RequestMethod.GET)
	public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		userDAO.deleteUser(id);
		
		response.sendRedirect(servletContext.getContextPath() + "/"+GlobalConfig.HOOD_LINK+"/users");
	}

}
