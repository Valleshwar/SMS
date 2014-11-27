package com.cts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bean.RegistrationBean;
import com.cts.dao.IRegistrationDAO;


@Service("registrationService")
public class RegistrationService implements IRegistrationService {

	@Autowired
	public IRegistrationDAO registrationDAO;
	
	
	public IRegistrationDAO getRegistrationDAO() {
		return registrationDAO;
	}

	public void setRegistrationDAO(IRegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
	}


	@Override
	public boolean addEmployee(RegistrationBean registrationBean) {
		return registrationDAO.addEmployee(registrationBean);
	}

	@Override
	public List<RegistrationBean> newRequests() {
		return registrationDAO.newRequests();
	}

}
