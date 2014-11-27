package com.cts.service;

import java.util.List;

import com.cts.bean.RegistrationBean;

public interface IRegistrationService {

	boolean addEmployee(RegistrationBean registrationBean);

	public List<RegistrationBean> newRequests();

}
