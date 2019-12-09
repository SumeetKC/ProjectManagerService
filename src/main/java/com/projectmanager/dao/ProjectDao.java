/**
 * 
 */
package com.projectmanager.dao;

import java.util.List;

import com.projectmanager.model.Project;

/**
 * @author SumeetK
 *
 */

public interface ProjectDao {
	
	// Method to add project
	public Project addProject(Project project);
	
	// Method to update project
	public Project updateProject(Project project);
	
	// Method to get tasks list from the database
	public List<Project> getProjects();
	
	// Method to get project
	public Project getProject(int id);
	
	public Project updateProjectStatus(Project project);

}
