package com.cts.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cts.bean.ApplicationBean;
import com.cts.bean.UserBean;


@Controller
public class UserController {
	
	private UserBean userBean;
	
	@RequestMapping(value="/adminhome", method = RequestMethod.GET)
	public ModelAndView initAdmin(HttpServletRequest request, HttpServletResponse response){
		
		ApplicationBean applicationBean=new ApplicationBean();
		userBean=(UserBean)request.getSession().getAttribute("userID");
		applicationBean.setUserBean(userBean);		
		return new ModelAndView("adminhome","applicationBean",applicationBean);
		
	}
	@RequestMapping(value="/managerhome", method = RequestMethod.GET)
	public ModelAndView initManager(HttpServletRequest request, HttpServletResponse response){
		
		ApplicationBean applicationBean=new ApplicationBean();
		userBean=(UserBean) request.getSession().getAttribute("userID");
		applicationBean.setUserBean(userBean);		
		return new ModelAndView("managerhome","applicationBean",applicationBean);
		
	}
	@RequestMapping(value="/employeehome", method = RequestMethod.GET)
	public ModelAndView initEmployee(HttpServletRequest request, HttpServletResponse response){
		
		ApplicationBean applicationBean=new ApplicationBean();
		userBean=(UserBean) request.getSession().getAttribute("userID");
		applicationBean.setUserBean(userBean);		
		return new ModelAndView("employeehome","applicationBean",applicationBean);
		
	}	
//	@RequestMapping(value="/Employee_Registration", method = RequestMethod.GET)
//	public ModelAndView initRegistration(HttpServletRequest request, HttpServletResponse response){
//		ApplicationBean applicationBean=new ApplicationBean();
//		return new ModelAndView("Employee_Registration","applicationBean",applicationBean);
//	}
//	
	

}
