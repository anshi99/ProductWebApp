package dao;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import constants.Constants;
import models.Product;
import models.User;

public class ProductDao {
	private HttpSession httpSession = null;
	private Session session = null;

	/**
	 * Constructor
	 * @param httpSession
	 */
	public ProductDao(HttpSession httpSession) {
		Configuration connection = new Configuration().configure().addAnnotatedClass(Product.class)
				.addAnnotatedClass(User.class);

		ServiceRegistry registory = (ServiceRegistry) new StandardServiceRegistryBuilder().applySettings(connection.getProperties())
				.build();

		SessionFactory factory = connection.buildSessionFactory(registory);
		session = factory.openSession();
		this.httpSession = httpSession;
	}

	/**
	 * Inserting Product into database
	 * @param title
	 * @param quantity
	 * @param size
	 * @param imgUrl
	 * @param user
	 * 
	 */
	public void insertProduct(String title, int quantity, int size, String imgUrl, User user) {
		try {
			
			Product product = new Product();
			product.setTitle(title);
			product.setQuantity(quantity);
			product.setSize(size);
			product.setImgUrl(imgUrl);
			product.setUser(user);
			// Start a new transaction if not yet started
			if (!session.getTransaction().isActive())
				session.getTransaction().begin();
			// save the product data in database
			session.persist(product);
			// commit the transaction
			session.getTransaction().commit();
			
			updateUserSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Getting the Product with Id
	 * @param id
	 * @return
	 * 
	 */
	public Product getProduct(int id) {
		try {
			// Create a new get product query and set id attribute for query
			Query<Product> querytoDB = session.createQuery(Constants.GET_PRODUCT_QUERY, Product.class);
			querytoDB.setParameter(Constants.ID, id);
			// return unique result for user
			return querytoDB.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method to update the product details
	 * @param id
	 * @param title
	 * @param quantity
	 * @param size
	 * @param imgUrl
	 * @param user
	 * 
	 */
	public void updateProduct(String id, String title, int quantity, int size, String imgUrl, User user) {
		try {
			// Checking if product already exists in the database
			if (getProduct(Integer.parseInt(id)) != null) {
				Product product = session.get(Product.class, Integer.parseInt(id));
				product.setTitle(title);
				product.setQuantity(quantity);
				product.setSize(size);
				product.setImgUrl(imgUrl);
				product.setUser(user);

				// Start a transaction if not yet started
				if (!session.getTransaction().isActive())
					session.getTransaction().begin();
				// Update the product details
				session.update(product);
				// Commit the transaction
				session.getTransaction().commit();

				updateUserSession();
			} else {
				httpSession.setAttribute(Constants.ID_ERROR, Constants.ID_DOES_NOT_EXISTS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  Deleting a product
	 * @param id
	 */
	public void deleteProduct(int id) {
		try {
			Product product = getProduct(id);
			// Checking if the product exists in the database
			if (product != null) {
				
				// Start a transaction if not yet started
				if (!session.getTransaction().isActive())
					session.getTransaction().begin();

				session.delete(product);
				
				session.getTransaction().commit();
				
				updateUserSession();
			} else {
				httpSession.setAttribute(Constants.ID_ERROR, Constants.ID_DOES_NOT_EXISTS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateUserSession() {
		// Update User Attributes in HTTPSession
		UserDao userDao = new UserDao();
		httpSession.setAttribute(Constants.USER_SESSION,
				userDao.getUser(((User) httpSession.getAttribute(Constants.USER_SESSION)).getUsername()));

	}
}
