package com.cts.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.cts.bean.RegistrationBean;
import com.cts.service.IRegistrationService;
import com.sun.org.apache.xpath.internal.operations.Mod;

@Controller
public class RegistrationController {

	private RegistrationBean registrationBean;
	
	
	@Autowired
	private IRegistrationService registrationService;
	
	
	public IRegistrationService getRegistrationService() {
		return registrationService;
	}

	public void setRegistrationService(IRegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	/** Navigates to the register page */
	@RequestMapping(value="/registration",method=RequestMethod.GET)
	public ModelAndView initRegistration() {
		RegistrationBean registrationBean=new RegistrationBean();
			return new ModelAndView("employeeregistration","registerDetails",registrationBean);
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ModelAndView doRegistration(@ModelAttribute("registerDetails") @Valid RegistrationBean registrationBean, 
			BindingResult result,HttpServletRequest request,HttpServletResponse response){
			
		ValidationUtils.rejectIfEmptyOrWhitespace(result, "employeeID","employeeID.required", "Employee ID Can't Be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(result, "employeeName","employeeName.required", "EmployeeName Can't Be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(result, "address","address.required", "Address Can't Be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(result, "mobile","mobile.required", "mobile Can't Be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(result, "email","email.required" ,"email Can't Be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(result, "designation","designation.required", "Designation Can't Be empty");
		
		if(result.hasErrors())
		{
			return new ModelAndView("employeeregistration","registerDetails", registrationBean);
		}
		else
		{

			boolean addEmployee=registrationService.addEmployee(registrationBean);
			if(addEmployee==true){
				return new ModelAndView("registrationsuccess");
			}
			else
			{
				return new ModelAndView("employeeregistration","registerDetails", registrationBean);
			}
		}
	}
	
	@RequestMapping(value="/requests",method=RequestMethod.GET)
	public ModelAndView newRequests(){
		ModelAndView modelAndView =new ModelAndView("newrequests");
		List<RegistrationBean> displayRequests=registrationService.newRequests();
		modelAndView.addObject("displayRequests",displayRequests);				
		return modelAndView;
		
	}

}
