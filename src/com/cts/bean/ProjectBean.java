package com.cts.bean;

public class ProjectBean {

	private String selectedID;
	public String getSelectedID() {
		return selectedID;
	}
	public void setSelectedID(String selectedID) {
		this.selectedID = selectedID;
	}
	
	
	private String projectID;
	private String projectName;
	private String managerID;
	public String getProjectID() {
		return projectID;
	}
	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getManagerID() {
		return managerID;
	}
	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}
	
}
