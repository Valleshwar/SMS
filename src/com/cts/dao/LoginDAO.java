package com.cts.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



import com.cts.bean.UserBean;

@Repository("loginDAO")
public class LoginDAO implements ILoginDAO {
	
	private JdbcTemplate jdbcTemplate;
	

	@Autowired
	public void setDataSource(DataSource datasource) {
		this.jdbcTemplate=new JdbcTemplate(datasource);
		
	}
	
	public List<String> authenitcateUser(UserBean userBean) {
		
		List<String> details;
		String name=null,role=null;
		String querForDesig="select designation from employee_details where Employee_id=? and Password=?";
//		String queryForName="select employee_name from employee_details where Employee_id="+userBean.getUserID()+" and Password='"+userBean.getPassword()+"'";
		details=jdbcTemplate.queryForList(querForDesig,new Object[]{Integer.parseInt(userBean.getUserID()),userBean.getPassword()},String.class);
//		role=(String)jdbcTemplate.queryForObject(querForDesig,String.class);
//		name=(String)jdbcTemplate.queryForObject(queryForName,String.class);
//		String querForName="select employee_Name from employee_details where Employee_id=? and Password=?";
//		details=jdbcTemplate.queryForList(querForName,new Object[]{userBean.getUserID(),userBean.getPassword()},String.class);	
//		System.out.println(role);
		return details;
	}

}
