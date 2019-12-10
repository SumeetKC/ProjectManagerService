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

import com.projectmanager.model.ParentTask;
import com.projectmanager.model.Task;

@Repository
public class TaskDaoImpl implements TaskDao {

	/** Initialize the LOGGER object */
	private static final Log logger = LogFactory.getLog(TaskDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Task addTask(Task task) {
		// TODO Auto-generated method stub
		logger.info("Inserting Task and Parent in database");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int parentId;
		String parentName;
		try {
			tx = session.beginTransaction();
			ParentTask parentTask = new ParentTask();
			parentName = task.getParentTask();
			if (parentName != null) {
				// Setting parent task from task object
				parentTask.setParentTask(parentName);
				// Saving parent task into table
				parentId = (int) session.save(parentTask);

				// Setting parent id in task object
				task.setParentId(parentId);

			}
			session.save(task);
			tx.commit();

		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			logger.error("Exception while inserting the task: " + ex);
			throw ex;
		} finally {
			session.close();
		}
		logger.info("Task Added Successfully.");
		return task;

	}

	@Override
	public Task updateTask(Task task) {
		// TODO Auto-generated method stub
		logger.info("Updating Task and Parent");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			ParentTask parentTask = new ParentTask();
			int parentId= task.getParentId();
			String parentName = task.getParentTask();
			parentTask.setParentId(parentId);
			parentTask.setParentTask(parentName);
			// Saving parent task into table
			session.saveOrUpdate(parentTask);
			session.saveOrUpdate(task);
			tx.commit();

		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			logger.error("Exception while updating the task: " + ex);
			throw ex;
		} finally {
			session.close();
		}
		logger.info("Task Updated Successfully.");
		return task;

	}

	@Override
	public List<Task> getTasks() {
		// TODO Auto-generated method stub
		logger.info("Getting List of Tasks From The Database");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Task> taskList = new ArrayList<Task>();
		try {
			tx = session.beginTransaction();
			taskList = session.createQuery("from Task", Task.class).list();
			tx.commit();

		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			logger.error("Exception while retrieving the task list : " + ex);
			throw ex;
		} finally {
			session.close();
		}
		logger.info("Tasks List Retrieved Successfully.");
		return taskList;
	}

	@Override
	public Task updateEndTask(Task task) {
		// TODO Auto-generated method stub
		logger.info("Updating End Task Status for the task");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			task.setStatus(true);
			session.saveOrUpdate(task);
			tx.commit();

		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			logger.error("Exception while updating the end task status : " + ex);
			throw ex;
		} finally {
			session.close();
		}
		logger.info("Task End Status Updated Successfully");
		return task;
	}
	
	@Override
	public Task getTask(int id) {
		// TODO Auto-generated method stub
		logger.info("Getting a Task Based on The Key Passed");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Task task = new Task();
		try {
			tx = session.beginTransaction();
			task = session.get(Task.class, id);
			tx.commit();

		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			logger.error("Exception while retrieving the task: " + ex);
			throw ex;
		} finally {
			session.close();
		}
		logger.info("Task Retrieved Successfully from the Database");
		return task;
	}


}
