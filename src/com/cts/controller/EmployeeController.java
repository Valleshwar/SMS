package com.cts.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cts.bean.EmployeeBean;
import com.cts.bean.ProjectBean;
import com.cts.bean.RegistrationBean;
import com.cts.service.IEmployeeService;
import com.cts.service.IProjectService;

@Controller
public class EmployeeController {
	
	private EmployeeBean employeeBean;
	private RegistrationBean registrationBean;
	private ProjectBean projectBean;
	
	
	@Autowired
	private IEmployeeService employeeService;

	public IEmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	
	@RequestMapping(value="/manageemployee",method=RequestMethod.GET)
	public ModelAndView initEmployeeMapping(){
		ModelAndView modelAndView=new ModelAndView("manageemployees");			
		return modelAndView;
		}
	
	@RequestMapping(value="/managerequests",method=RequestMethod.GET)
	public ModelAndView manageRequests(){
		ModelAndView modelAndView =new ModelAndView("managerequestshome");
		List<RegistrationBean> displayRequests=employeeService.newRequests();
		modelAndView.addObject("displayRequests",displayRequests);				
		return modelAndView;
		
	}
	
	
	@RequestMapping(value="/mappingEmployeeHome",method=RequestMethod.GET)
	public ModelAndView mappingEmployeeHome(@RequestParam("employeeID") String employeeID){
		EmployeeBean employeeBean=new EmployeeBean();
		RegistrationBean employee=new RegistrationBean();
		employee.setEmployeeID(employeeID);
		List<EmployeeBean> managers=employeeService.showManagers();
		ModelAndView modelAndView=new ModelAndView("selectmanager","selectedManager",employeeBean);		
		modelAndView.addObject("employee", employee);
		modelAndView.addObject("managers",managers);
	
		return modelAndView;
	}
	
	@RequestMapping(value="/allocateManager",method=RequestMethod.POST)
	public ModelAndView changeManger(@RequestParam("empID") String employeeID,
			@ModelAttribute("selectedManager") EmployeeBean employeeBean,
			BindingResult result,HttpServletRequest request,HttpServletResponse response){
		String managerID=employeeBean.getEmployeeID();
		System.out.println(employeeID);
		RegistrationBean employeeDetais=employeeService.getEmployeeDetails(employeeID);
		ModelAndView modelAndView=new ModelAndView("managerallocationsuccess");
		boolean managerAllocated=employeeService.allocateManager(employeeDetais,managerID);
		System.out.println(managerAllocated);
		if(managerAllocated==true){
			ProjectBean projectBean=new ProjectBean();
			modelAndView.addObject("employeeID",employeeID);
			modelAndView.addObject("managerID",managerID);
			List<ProjectBean> displayProjects=employeeService.viewProjects(managerID);
		
			modelAndView.addObject("displayProjects",displayProjects);
			return modelAndView;
		}
		else
		{
			return mappingEmployeeHome(employeeID);
		}
		
	}
		
	
}
