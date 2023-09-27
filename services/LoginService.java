package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.Constants;
import dao.UserDao;
import models.User;

/**
 * Servlet implementation of class LoginService
 */
public class LoginService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Getting Username and password from the form post request
		String username = request.getParameter(Constants.USERNAME);
		String password = request.getParameter(Constants.PASSWORD);

		// Check if the username field is not empty
		if (username == null || username.trim() == "") {
			// Set username error attribute field in session 
			request.getSession().setAttribute(Constants.USERNAME_ERROR, Constants.FIELD_CANNOT_BE_EMPTY);
			// redirect to login page
			response.sendRedirect("login.jsp");
			return;
		}

		// Check if the password field is not empty
		if (password == null || password.trim() == "") {
			// Set password error attribute field in session 
			request.getSession().setAttribute(Constants.PASSWORD_ERROR, Constants.FIELD_CANNOT_BE_EMPTY);
			// redirect to login page
			response.sendRedirect("login.jsp");
			return;
		}

		// call getUser() method from UserDao class
		UserDao userDao = new UserDao();
		User user = userDao.getUser(username);

		// Check if the user object is null
		if (user == null) {
			// Return that the user does not exists in the database
			request.getSession().setAttribute(Constants.USERNAME_ERROR, Constants.USERNAME_DOES_NOT_EXISTS);
			response.sendRedirect("login.jsp");
			return;
		}else if(!user.getPassword().equals(password)) {
			// Check if the username matches to password
			// else return incorrect password
			request.getSession().setAttribute(Constants.PASSWORD_ERROR, Constants.INCORRECT_PASSWORD);
			response.sendRedirect("login.jsp");
			return;
		}
		// if it is a valid user save user to session 
		request.getSession().setAttribute(Constants.USER_SESSION, user);
		// send user to the products jsp page
		response.sendRedirect("products.jsp");
	}

}
