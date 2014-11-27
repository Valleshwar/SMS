package com.cts.dao;

import java.util.List;

import com.cts.bean.EmployeeBean;
import com.cts.bean.ProjectBean;
import com.cts.bean.RegistrationBean;

public interface IEmployeeDAO {

	public List<RegistrationBean> newRequests();

	public List<EmployeeBean> showmanagers();

	public boolean allocateManager(RegistrationBean employeeDetails, String employeeID);


	public List<ProjectBean> viewProjects(String managerID);

	public RegistrationBean getEmployeeDetails(String employeeID);

}
