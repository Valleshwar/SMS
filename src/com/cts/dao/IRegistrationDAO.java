package com.cts.dao;

import java.util.List;

import com.cts.bean.RegistrationBean;

public interface IRegistrationDAO {

	boolean addEmployee(RegistrationBean registrationBean);

	public List<RegistrationBean> newRequests();
	

}
