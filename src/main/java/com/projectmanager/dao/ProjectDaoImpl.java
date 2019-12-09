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

import com.projectmanager.model.Project;

@Repository
public class ProjectDaoImpl implements ProjectDao {

	/** Initialize the LOGGER object */
	private static final Log logger = LogFactory.getLog(ProjectDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Project addProject(Project project) {
		// TODO Auto-generated method stub
		logger.info("Inserting Project in database");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.save(project);
			tx.commit();

		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			logger.error("Exception while inserting the project: " + ex);
			throw ex;
		} finally {
			session.close();
		}
		logger.info("Project Added Successfully.");
		return project;

	}

	@Override
	public Project updateProject(Project project) {
		// TODO Auto-generated method stub
		logger.info("Updating Project");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(project);
			tx.commit();

		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			logger.error("Exception while updating the project: " + ex);
			throw ex;
		} finally {
			session.close();
		}
		logger.info("Project Updated Successfully.");
		return project;

	}

	@Override
	public List<Project> getProjects() {
		// TODO Auto-generated method stub
		logger.info("Getting List of Projects From The Database");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Project> projectList = new ArrayList<Project>();
		try {
			tx = session.beginTransaction();
			projectList = session.createQuery("from Project", Project.class).list();
			tx.commit();

		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			logger.error("Exception while retrieving the project list : " + ex);
			throw ex;
		} finally {
			session.close();
		}
		logger.info("Projects List Retrieved Successfully.");
		return projectList;
	}

	@Override
	public Project getProject(int id) {
		// TODO Auto-generated method stub
		logger.info("Getting a Project Based on The Key Passed");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Project project = new Project();
		try {
			tx = session.beginTransaction();
			project = session.get(Project.class, id);
			tx.commit();

		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			logger.error("Exception while retrieving the project: " + ex);
			throw ex;
		} finally {
			session.close();
		}
		logger.info("Project Retrieved Successfully from the Database");
		return project;
	}

	public Project updateProjectStatus(Project project) {
		// TODO Auto-generated method stub
		logger.info("Updating End Status for the project");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			project.setProjectEndStatus(true);
			session.saveOrUpdate("projectEndStatus", project);
			tx.commit();

		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			logger.error("Exception while updating the end project status : " + ex);
			throw ex;
		} finally {
			session.close();
		}
		logger.info("Project End Status Updated Successfully");
		return project;
	}


}
