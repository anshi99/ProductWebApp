package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.Constants;
import dao.ProductDao;
import models.User;
import utils.ProductValidation;

public class ProductService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("products.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println(request.getParameter(Constants.ID));
			// Create a product validation object
			ProductValidation validation = new ProductValidation(request.getSession());
			
			ProductDao productDao = new ProductDao(request.getSession());
			
			// Check if it's a Insert or Update request
			if (request.getParameter(Constants.ID) == null || request.getParameter(Constants.ID).equals("")) {
				// For Insert Request
				// Get all attribute from the form
				String title = request.getParameter(Constants.TITLE);
				String quantity = request.getParameter(Constants.QUANTITY);
				String size = request.getParameter(Constants.SIZE);
				String imgUrl = request.getParameter(Constants.IMAGE_URL);
				// Get user attribute from the current session
				User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);

				// validate all form field
				if (validation.validateTitle(title) && validation.validateQuantity(quantity)
						&& validation.validateSize(size) && validation.validateImgUrl(imgUrl)) {

					// insert product data into products database
					productDao.insertProduct(title, Integer.parseInt(quantity), Integer.parseInt(size), imgUrl, user);
				}
			} else {
				// For UPDATE request
				// Get all attribute from the form
				String id = request.getParameter(Constants.ID);
				String title = request.getParameter(Constants.TITLE);
				String quantity = request.getParameter(Constants.QUANTITY);
				String size = request.getParameter(Constants.SIZE);
				String imgUrl = request.getParameter(Constants.IMAGE_URL);
				// Get user attribute from the current session
				User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);

				// validate all form field
				if (validation.validateID(id) && validation.validateTitle(title) && validation.validateQuantity(quantity)
						&& validation.validateSize(size) && validation.validateImgUrl(imgUrl)) {
					
					// insert product data into products database
					productDao.updateProduct(id, title, Integer.parseInt(quantity), Integer.parseInt(size), imgUrl, user);
				}
			}
			// Redirect user again to Products page
			response.sendRedirect("products.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
