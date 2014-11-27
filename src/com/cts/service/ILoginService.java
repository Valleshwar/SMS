package com.cts.service;

import java.util.List;

import com.cts.bean.UserBean;

public interface ILoginService {
	
	public List<String> authenticateUser(UserBean userBean);

}
