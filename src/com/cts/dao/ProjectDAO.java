package com.cts.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sun.java2d.opengl.OGLDrawImage;
import sun.net.www.content.text.plain;

import com.cts.bean.EmployeeBean;
import com.cts.bean.ProjectBean;


@Repository
public class ProjectDAO implements IProjectDAO {

private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource datasource) {
		this.jdbcTemplate=new JdbcTemplate(datasource);
		
	}

	@Override
	public List<String> getManagers() {
		String SQL="SELECT Employee_id from sms.Employee_Details where Designation='Manager'";
		List<String> managers = new ArrayList<String>(); 
		List<Map<String, Object>> rows=jdbcTemplate.queryForList(SQL);
		for(Map row:rows)
		{
			String managerID= row.get("Employee_id").toString();
			managers.add(managerID);
		}
		return managers;
	}

	@Override
	public boolean enrollProject(ProjectBean projectBean) {
		boolean enrolledProject=false;
		String SQL="INSERT into project_details(project_id,project_Name,manager_Id) values(?,?,?)";
		Object[] params=new Object[]{projectBean.getProjectID(),projectBean.getProjectName(),
				(projectBean.getManagerID().equalsIgnoreCase("--select--")) ? null : Integer.parseInt(projectBean.getManagerID())};
		int[] types=new int[]{Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		 int row=jdbcTemplate.update(SQL,params,types);
		 if(row==1)
		 {
			 enrolledProject=true;
		 }
		 
		return enrolledProject;
	}

	@Override
	public List<ProjectBean> viewProject() {
		String SQL="SELECT * from sms.project_details";
		List<ProjectBean> projects=new ArrayList<ProjectBean>();
		List<Map<String, Object>> rows=jdbcTemplate.queryForList(SQL);
		for(Map row:rows){
			ProjectBean projectBean=new ProjectBean();
			projectBean.setProjectID((String)row.get("Project_Id"));
			projectBean.setProjectName((String)row.get("Project_Name"));
			projectBean.setManagerID(((row.get("Manager_Id")==null) ? "null" : (row.get("Manager_Id")).toString()));
			projects.add(projectBean);
		}
		
		return projects;
	}

	@Override
	public ProjectBean getProjectDetails(String projectID) {
	
		String SQL="SELECT * from sms.project_details where Project_Id='"+projectID+"'";
		ProjectBean projectBean=new ProjectBean();
		List<Map<String, Object>> rows=jdbcTemplate.queryForList(SQL);
		for(Map row:rows){
			projectBean.setProjectID((String)row.get("Project_Id"));
			projectBean.setProjectName((String)row.get("Project_Name"));
			projectBean.setManagerID(((row.get("Manager_Id")==null) ? "null" : (row.get("Manager_Id")).toString()));
		}
		return projectBean;
	}

	@Override
	public List<EmployeeBean> showManagers() {
		
		String SQL="SELECT Employee_id,Employee_Name,Mobile,Email from employee_details where designation='Manager'";
		List<EmployeeBean> managers=new ArrayList<EmployeeBean>();
		List<Map<String, Object>> rows=jdbcTemplate.queryForList(SQL);
		for(Map row:rows)
		{
			EmployeeBean employeeBean=new EmployeeBean();
			employeeBean.setEmployeeID((row.get("Employee_id").toString()));
			employeeBean.setEmployeeName((String) (row.get("Employee_Name")));
			employeeBean.setMobile((String) (row.get("Mobile")));
			employeeBean.setEmail((String) (row.get("Email")));
			managers.add(employeeBean);
		}
		
		return managers;
	}

	@Override
	public boolean changeManager(String projectID, String managerID) {
		boolean managerChanged=false;
		System.out.println("In DAO");
		String SQL="UPDATE sms.project_details set Manager_Id=? where Project_Id=?";
		Object[] params=new Object[]{Integer.parseInt(managerID),projectID};
		int[] types=new int[]{Types.INTEGER,Types.VARCHAR};
		int row=jdbcTemplate.update(SQL, params, types);
		if(row==1)
		{
			String SQL2="UPDATE sms.Employee_details set Project_Id=? where Employee_id=?";
			Object[] params2=new Object[]{projectID,Integer.parseInt(managerID)};
			int[] types2=new int[]{Types.VARCHAR,Types.INTEGER};
			int row2=jdbcTemplate.update(SQL2, params2, types2);
			if(row2==1){
				managerChanged=true;
			}
		}
		
		return managerChanged;
	}

}
