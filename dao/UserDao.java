package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import constants.Constants;
import models.Product;
import models.User;

public class UserDao {
	private Session session = null;

	// Constructor
	public UserDao() {
		Configuration connection = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Product.class);

		ServiceRegistry registory = (ServiceRegistry) new StandardServiceRegistryBuilder().applySettings(connection.getProperties())
				.build();

		SessionFactory factory = connection.buildSessionFactory(registory);
		session = factory.openSession();
	}

	/**
	 * Getting User From the Database
	 * @param username
	 * @return
	 */
	public User getUser(String username) {
		User user = null;
		try {
			
			Query<User> querytoDB = session.createQuery(Constants.GET_USER_QUERY, User.class);
			// Set User query parameters
			querytoDB.setParameter("u", username);
			// Get User
			user = querytoDB.uniqueResult();
			// Close session
			session.close();
		} catch (Exception e) {
			System.out.println(Constants.PASSWORD_ERROR);
		}
		// Return User
		return user;
	}
}
