package com.cts.service;

import java.util.List;

import com.cts.bean.EmployeeBean;
import com.cts.bean.ProjectBean;

public interface IProjectService {

	public List<String> getManagers();

	public boolean enrollProject(ProjectBean projectBean);

	public List<ProjectBean> viewProjects();

	public ProjectBean getProjectDetails(String projectID);

	public List<EmployeeBean> showManagers();

	public boolean changeManager(String projectID, String mangerID);

}
