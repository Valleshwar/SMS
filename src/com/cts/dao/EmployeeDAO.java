package com.cts.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cts.bean.EmployeeBean;
import com.cts.bean.ProjectBean;
import com.cts.bean.RegistrationBean;

@Repository("employeeDAO")
public class EmployeeDAO implements IEmployeeDAO {
	
	private JdbcTemplate jdbcTemplate;
	

	@Autowired
	public void setDataSource(DataSource datasource) {
		this.jdbcTemplate=new JdbcTemplate(datasource);
		
	}


	@Override
	public List<RegistrationBean> newRequests() {
		String SQL="SELECT * from sms.registration";
		List<RegistrationBean> requests=new ArrayList<RegistrationBean>();
		List<Map<String, Object>> rows=jdbcTemplate.queryForList(SQL);
		for(Map row:rows){
			RegistrationBean registrationBean=new RegistrationBean();
			registrationBean.setEmployeeID((row.get("Employee_ID").toString()));
			registrationBean.setEmployeeName((String)row.get("Employee_Name"));
			registrationBean.setAddress((String)row.get("Address"));
			registrationBean.setMobile((String)row.get("Mobile"));
			registrationBean.setEmail((String)row.get("Email"));
			registrationBean.setDesignation((String)row.get("Designation"));
			requests.add(registrationBean);
			}
		return requests;		
		
	}


	@Override
	public List<EmployeeBean> showmanagers() {
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


	public RegistrationBean getEmployeeDetails(String employeeID) {
		String SQL="SELECT * from registration where Employee_id="+Integer.parseInt(employeeID)+"";
		RegistrationBean employeeDetails=new RegistrationBean();
		List<Map<String, Object>> rows=jdbcTemplate.queryForList(SQL);
		for(Map row:rows)
		{
			employeeDetails.setEmployeeID(row.get("Employee_id").toString());
			employeeDetails.setEmployeeName((String) (row.get("Employee_Name")));
			employeeDetails.setAddress((String) (row.get("Address")));
			employeeDetails.setMobile((String) (row.get("Mobile")));
			employeeDetails.setEmail((String) (row.get("Email")));
			employeeDetails.setDesignation((String) (row.get("Designation")));
			
		}
		return employeeDetails;
	}


	
	@Override
	public boolean allocateManager(RegistrationBean employeeDetails, String managerID) {
		boolean managerAllocated=false;
		String SQL="Insert into employee_details(Employee_id,Employee_Name,Address,Mobile,Email,Designation,Manager_id)" +
				" values(?,?,?,?,?,?,?)";
		Object[] params=new Object[]{Integer.parseInt(employeeDetails.getEmployeeID()),
				employeeDetails.getEmployeeName(),employeeDetails.getAddress(),
				employeeDetails.getMobile(),employeeDetails.getEmail(),
				employeeDetails.getDesignation(),Integer.parseInt(managerID)};
		int[] types=new int[]{Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		
		int row=jdbcTemplate.update(SQL, params, types);
		if(row==1)
		{
			managerAllocated=true;
		}
		return managerAllocated;
	}


	@Override
	public List<ProjectBean> viewProjects(String managerID) {
		System.out.println(managerID);
		String SQL="SELECT * from sms.project_details having Manager_id="+Integer.parseInt(managerID)+"";
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


	
	
	

	
}
