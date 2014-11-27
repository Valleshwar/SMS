package com.cts.bean;

public class ApplicationBean {
	
	private UserBean userBean;
	private ProjectBean projectBean;
	private RegistrationBean registrationBean;

	public ProjectBean getProjectBean() {
		return projectBean;
	}

	public void setProjectBean(ProjectBean projectBean) {
		this.projectBean = projectBean;
	}

	public RegistrationBean getRegistrationBean() {
		return registrationBean;
	}

	public void setRegistrationBean(RegistrationBean registrationBean) {
		this.registrationBean = registrationBean;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	
}