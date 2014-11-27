package com.cts.dao;

import java.util.List;

import javax.sql.DataSource;

import com.cts.bean.UserBean;

public interface ILoginDAO {
	
	public abstract void setDataSource(DataSource datasource);
	public abstract List<String> authenitcateUser(UserBean userBean);
	

}
