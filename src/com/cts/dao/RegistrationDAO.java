package com.cts.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cts.bean.RegistrationBean;

@Repository("registrationDAO")
public class RegistrationDAO implements IRegistrationDAO{ 
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource datasource) {
		this.jdbcTemplate=new JdbcTemplate(datasource);
		
	}

	@Override
	public boolean addEmployee(RegistrationBean registrationBean) {
		String SQL="Insert into sms.registration(Employee_id,Employee_Name,Address,Mobile,Email,Designation) values(?,?,?,?,?,?)";
		boolean employeeAdded=false;
		Object[] params = new Object[] { Integer.parseInt(registrationBean.getEmployeeID()),registrationBean.getEmployeeName(),
								registrationBean.getAddress(),registrationBean.getMobile(),registrationBean.getEmail(),
								registrationBean.getDesignation()};
		  int[] types = new int[] {Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		  int row=jdbcTemplate.update(SQL,params,types);
		  if(row==1)
		  {
			employeeAdded=true;  
		  }
		
		return employeeAdded;
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

}
