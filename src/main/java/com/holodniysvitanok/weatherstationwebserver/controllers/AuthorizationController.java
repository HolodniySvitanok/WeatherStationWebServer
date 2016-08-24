package com.holodniysvitanok.weatherstationwebserver.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.holodniysvitanok.weatherstationwebserver.dao.UserDAO;
import com.holodniysvitanok.weatherstationwebserver.entity.User;

@Controller
public class AuthorizationController {

	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value = "/authorization", method = RequestMethod.POST)
	public ModelAndView authorizatio(ModelAndView model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String login = request.getParameter("lgn");
		String password = request.getParameter("pswd");

		User user = null;
		if (!val(login) || !val(password)) {
			model.setViewName("authorization");
			model.addObject("errorMessage", "не коректные данные");
			return model;
		}

		try{
			user = userDAO.authorizationUser(new User(login, password));
		}catch (Exception e) {
			model.setViewName("authorization");
			model.addObject("errorMessage", "неверный логин или пароль");
			return model;
		}

		request.getSession().setAttribute("user", user);
		response.sendRedirect(request.getServletContext().getContextPath()+"/hood/");
	
		return null;
	}

	
	
	
	private boolean val(String str) {
		if (str.trim().length() > 4 && str.trim().length() < 10) {
			return true;
		} else {
			return false;
		}
	}
}
