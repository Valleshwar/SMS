package com.cts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bean.EmployeeBean;
import com.cts.bean.ProjectBean;
import com.cts.dao.IProjectDAO;


@Service("projectService")
public class ProjectService implements IProjectService{
	
	@Autowired
	private IProjectDAO projectDAO;

	public IProjectDAO getProjectDAO() {
		return projectDAO;
	}

	public void setProjectDAO(IProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	@Override
	public List<String> getManagers() {
		// TODO Auto-generated method stub
		return projectDAO.getManagers();
	}

	@Override
	public boolean enrollProject(ProjectBean projectBean) {
		
		return projectDAO.enrollProject(projectBean);
	}

	@Override
	public List<ProjectBean> viewProjects() {
		
		return projectDAO.viewProject();
	}

	@Override
	public ProjectBean getProjectDetails(String projectID) {
		
		return projectDAO.getProjectDetails(projectID);
	}

	@Override
	public List<EmployeeBean> showManagers() {
		
		return projectDAO.showManagers();
	}

	@Override
	public boolean changeManager(String projectID, String mangerID) {
		
		return projectDAO.changeManager(projectID,mangerID);
	}
	
	

}
