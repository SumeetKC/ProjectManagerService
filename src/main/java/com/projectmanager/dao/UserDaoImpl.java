/**
 * 
 */
package com.projectmanager.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.projectmanager.model.User;

/**
 * @author SumeetK
 *
 */
@Repository
public class UserDaoImpl implements UserDao {
	
	/** Initialize the LOGGER object */
	private static final Log logger = LogFactory.getLog(UserDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		logger.info("Inserting User in database");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();

		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			logger.error("Exception while inserting the user: " + ex);
			throw ex;
		} finally {
			session.close();
		}
		logger.info("User Added Successfully.");
		return user;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		logger.info("Updating User in database");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(user);
			tx.commit();

		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			logger.error("Exception while updating the user: " + ex);
			throw ex;
		} finally {
			session.close();
		}
		logger.info("User Updated Successfully.");
		return user;
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		logger.info("Getting List of Users From The Database");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<User> userList = new ArrayList<User>();
		try {
			tx = session.beginTransaction();
			userList = session.createQuery("from User", User.class).list();
			tx.commit();

		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			logger.error("Exception while retrieving the users list : " + ex);
			throw ex;
		} finally {
			session.close();
		}
		logger.info("Users List Retrieved Successfully.");
		return userList;
	}

	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		logger.info("Getting User Based on The Key Passed");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		User user = new User();
		try {
			tx = session.beginTransaction();
			user = session.get(User.class, id);
			tx.commit();

		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			logger.error("Exception while retrieving the user: " + ex);
			throw ex;
		} finally {
			session.close();
		}
		logger.info("User Retrieved Successfully from the Database");
		return user;
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		logger.info("Deleting User based on the id Passed");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		User user = getUser(id);
		try {
			tx = session.beginTransaction();
			session.delete(user);
			tx.commit();

		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			logger.error("Exception while deleting the user: " + ex);
			throw ex;
		} finally {
			session.close();
		}
		logger.info("User Deleted Successfully from the Database");
	}
	
	
	

}
