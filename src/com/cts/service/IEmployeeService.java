package com.cts.service;

import java.util.List;

import com.cts.bean.EmployeeBean;
import com.cts.bean.ProjectBean;
import com.cts.bean.RegistrationBean;

public interface IEmployeeService {

	public List<RegistrationBean> newRequests();

	public List<EmployeeBean> showManagers();

	public boolean allocateManager(RegistrationBean employeeDetais, String employeeID);

	public List<ProjectBean> viewProjects(String managerID);

	public RegistrationBean getEmployeeDetails(String employeeID);

}
