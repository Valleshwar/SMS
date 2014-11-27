package com.cts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bean.EmployeeBean;
import com.cts.bean.ProjectBean;
import com.cts.bean.RegistrationBean;
import com.cts.dao.IEmployeeDAO;

@Service("employeeService")
public class EmployeeService implements IEmployeeService {
	
	@Autowired
	
	private IEmployeeDAO employeeDAO;

	public IEmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	public void setEmployeeDAO(IEmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	@Override
	public List<RegistrationBean> newRequests() {
		
		return employeeDAO.newRequests();
	}

	@Override
	public List<EmployeeBean> showManagers() {
		
		return employeeDAO.showmanagers();
	}

	@Override
	public boolean allocateManager(RegistrationBean employeeDetails, String employeeID) {
		
		return employeeDAO.allocateManager(employeeDetails,employeeID);
	}

	@Override
	public List<ProjectBean> viewProjects(String managerID) {
		
		return employeeDAO.viewProjects(managerID);
	}

	@Override
	public RegistrationBean getEmployeeDetails(String employeeID) {
		
		return employeeDAO.getEmployeeDetails(employeeID);
	}

}
