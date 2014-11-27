package com.cts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cts.bean.UserBean;
import com.cts.dao.ILoginDAO;

@Service("loginService")
public class LoginService implements ILoginService {
	
	
	@Autowired
	private ILoginDAO loginDAO;
	
	public ILoginDAO getLoginDAO() {
		return loginDAO;
	}
	public void setLoginDAO(ILoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	
	public List<String> authenticateUser(UserBean userBean) {
	return loginDAO.authenitcateUser(userBean);
	}

}
