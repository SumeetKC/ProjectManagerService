package com.projectmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.projectmanager.dao.ProjectDao;
import com.projectmanager.dao.TaskDao;
import com.projectmanager.dao.UserDao;
import com.projectmanager.model.Project;
import com.projectmanager.model.Task;
import com.projectmanager.model.User;
import com.projectmanager.service.ProjectService;
import com.projectmanager.service.TaskService;
import com.projectmanager.service.UserService;

@SpringBootTest
class ProjectManagerServiceApplicationTests {

	@InjectMocks
	TaskService taskService;
	@InjectMocks
	UserService userService;
	@InjectMocks
	ProjectService projectService;
     
    @Mock
    TaskDao taskDao;
    @Mock
    UserDao userDao;
    @Mock
    ProjectDao projectDao;
    
    //******************************** Test Cases for Task*******************************
    @Test
    public void testAddTask() 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        Task task = new Task(1, 1, "parent", "taskname", new Date(), new Date(), 2, false);
        when(taskDao.addTask(task)).thenReturn(task);
         
        
        try {
        	Task task1 = taskDao.addTask(task);
        	assertEquals(task, task1);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("The exception is : " + e);
			e.printStackTrace();
		}
        
    }
    
    @Test
    public void testUpdateTask() 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        Task task = new Task(1, 1, "parent", "taskname", new Date(), new Date(), 2, false);
        when(taskDao.updateTask(task)).thenReturn(task);
         
        
        try {
        	Task task1 = taskDao.updateTask(task);
        	assertEquals(task, task1);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("The exception is : " + e);
			e.printStackTrace();
		}
        
    }
    
     
    @Test
    public void testGetTasks() 
    {
        // given
    	Task task1 = new Task(1, 1, "parent", "taskname", new Date(), new Date(), 2, false);
    	Task task2 = new Task(2, 2, "parent1", "taskname1", new Date(), new Date(), 3, true);
        ArrayList<Task> list = new ArrayList<Task>();
        list.add(task1);
        list.add(task2);
 
        when(taskDao.getTasks()).thenReturn(list);
 
        // when
        List<Task> result = taskDao.getTasks();
 
        // then
        assertEquals(result.size(), list.size());
        assertEquals(result.get(0).getTaskName(), list.get(0).getTaskName());
    }
    
    @Test
    public void testGetTask() throws ParseException 
    {
        // given
    	int taskId = 31;
    	try {
		Task task = new Task(31, 30, "Parent", "Task1", new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-30"), new SimpleDateFormat("yyyy-MM-dd").parse("2017-11-30"), 12, false);
    
        when(taskDao.getTask(taskId)).thenReturn(task);
 
        // when
        Task result = taskDao.getTask(taskId);
        
        // then
        assertEquals(result.getTaskName(), task.getTaskName());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
       
    }

    
  //******************************** Test Cases for User*******************************
    @Test
    public void testAddUser() 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        User user = new User(1, "User", "Name", 123456, 1, 1);
        when(userDao.addUser(user)).thenReturn(user);
         
        
        try {
        	User user1 = userDao.addUser(user);
        	assertEquals(user, user1);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("The exception is : " + e);
			e.printStackTrace();
		}
        
    }
    
    @Test
    public void testUpdateUser() 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        User user = new User(1, "User", "Name", 123456, 1, 1);
        when(userDao.addUser(user)).thenReturn(user);
         
        
        try {
        	User user1 = userDao.addUser(user);
        	assertEquals(user, user1);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("The exception is : " + e);
			e.printStackTrace();
		}
        
    }
    
     
    @Test
    public void testGetUsers() 
    {
        // given
    	User user = new User(1, "User", "Name", 123456, 1, 1);
    	User user1 = new User(5, "Test", "User", 788945, 2, 1);
        ArrayList<User> list = new ArrayList<User>();
        list.add(user);
        list.add(user1);
 
        when(userDao.getUsers()).thenReturn(list);
 
        // when
        List<User> result = userDao.getUsers();
 
        // then
        assertEquals(result.size(), list.size());
        assertEquals(result.get(0).getFirstName(), list.get(0).getFirstName());
    }
    
    @Test
    public void testGetUser() throws ParseException 
    {
        // given
    	int userId = 31;
    	try {
		User user = new User(31, "User", "Name", 123456, 1, 1);
    
        when(userDao.getUser(userId)).thenReturn(user);
 
        // when
        User result = userDao.getUser(userId);
        
        // then
        assertEquals(result.getFirstName(), user.getFirstName());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
       
    }
    
    
  //******************************** Test Cases for Project*******************************
    @Test
    public void testAddProject() throws ParseException 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        Project project = new Project(1, "Acis", new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-30"), new SimpleDateFormat("yyyy-MM-dd").parse("2017-11-30"), 2, true); 
        when(projectDao.addProject(project)).thenReturn(project);
         
        
        try {
        	Project project1 = projectDao.addProject(project);
        	assertEquals(project, project1);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("The exception is : " + e);
			e.printStackTrace();
		}
        
    }
    
    @Test
    public void testUpdateProject() throws ParseException 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        Project project = new Project(1, "Acis", new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-30"), new SimpleDateFormat("yyyy-MM-dd").parse("2017-11-30"), 2, true);
        when(projectDao.updateProject(project)).thenReturn(project);
        
        try {
        	Project project1 = projectDao.updateProject(project);
        	assertEquals(project, project1);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("The exception is : " + e);
			e.printStackTrace();
		}
        
    }
    
     
    @Test
    public void testGetProjects() throws ParseException 
    {
        // given
    	Project project = new Project(1, "Acis", new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-30"), new SimpleDateFormat("yyyy-MM-dd").parse("2017-11-30"), 2, true);
    	Project project1 = new Project(2, "Project", new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-26"), new SimpleDateFormat("yyyy-MM-dd").parse("2017-11-28"), 2, true);
        ArrayList<Project> list = new ArrayList<Project>();
        list.add(project);
        list.add(project1);
 
        when(projectDao.getProjects()).thenReturn(list);
 
        // when
        List<Project> result = projectDao.getProjects();
 
        // then
        assertEquals(result.size(), list.size());
        assertEquals(result.get(0).getProjectName(), list.get(0).getProjectName());
    }
    
    @Test
    public void testGetProject() throws ParseException 
    {
        // given
    	int projectId = 31;
    	try {
    		Project project = new Project(31, "Acis project", new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-30"), new SimpleDateFormat("yyyy-MM-dd").parse("2017-11-30"), 2, true);
    
        when(projectDao.getProject(projectId)).thenReturn(project);
 
        // when
        Project result = projectDao.getProject(projectId);
        
        // then
        assertEquals(result.getProjectName(), project.getProjectName());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
       
    }
    
}
