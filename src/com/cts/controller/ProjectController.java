package com.cts.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cts.bean.EmployeeBean;
import com.cts.bean.ProjectBean;
import com.cts.service.IProjectService;

@Controller
public class ProjectController {

	private ProjectBean projectBean;
	
	@Autowired
	private IProjectService projectService;

	public IProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}
	
	@RequestMapping(value="/manageprojects",method=RequestMethod.GET)
	public ModelAndView initprojectMapping(){
		return new ModelAndView("manageprojects");
			
	}
	
	@RequestMapping(value="/addproject",method=RequestMethod.GET)
	public ModelAndView addProjectHome(){
		ProjectBean projectBean=new ProjectBean();
		List<String> managers=projectService.getManagers();
		ModelAndView modelAndView=new  ModelAndView("addproject","projectDetails",projectBean);
		modelAndView.addObject("managers",managers);
		return modelAndView;		
	}	
	
	@RequestMapping(value="/enrollproject",method=RequestMethod.POST)
	public ModelAndView enrollProject(@ModelAttribute("projectDetails") @Valid ProjectBean projectBean,
			BindingResult result,HttpServletRequest request,HttpServletResponse response){
		
		ValidationUtils.rejectIfEmptyOrWhitespace(result, "projectID","projectID.required", "Proejct ID Can't Be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(result, "projectName","projectName.required", "Project Name Can't Be empty");
		if(result.hasErrors()){
			return new ModelAndView("addprojectfailure");
		}
		else
		{
			boolean enrolledProject=projectService.enrollProject(projectBean);
			if(enrolledProject==true)
			{
				return new ModelAndView("addprojectsuccess");
			}
			else
			{
				return new ModelAndView("addprojectfailure");
			}
		}
	}
	
		@RequestMapping(value="/viewprojects",method=RequestMethod.GET)
		public ModelAndView viewProjects(){
						
			ModelAndView modelAndView =new ModelAndView("viewprojects");
			List<ProjectBean> displayProjects=projectService.viewProjects();
			modelAndView.addObject("displayProjects",displayProjects);		
			return modelAndView;
		}
		
		@RequestMapping(value="/mappinghome",method=RequestMethod.GET)
		public ModelAndView getProjectDeails(@RequestParam("projectID") String projectID){
			ProjectBean project=new ProjectBean();
			EmployeeBean employeeBean=new EmployeeBean();
			project = projectService.getProjectDetails(projectID);	
			List<EmployeeBean> managers=projectService.showManagers();
			ModelAndView modelAndView = new ModelAndView("projectdetails","managerDetails",employeeBean);
			modelAndView.addObject("project",project);
			modelAndView.addObject("managers",managers);
			return modelAndView;
		}
		
		@RequestMapping(value="/changemanager",method=RequestMethod.POST)
		public ModelAndView changeManager(@RequestParam("projectID") String projectID,
				@ModelAttribute("managerDetails") EmployeeBean employeeBean,
				BindingResult result,HttpServletRequest request,HttpServletResponse response){
			String managerID=employeeBean.getSelectedID();
			System.out.println(projectID);
			System.out.println(managerID);
			System.out.println(managerID);
			boolean managerChanged=projectService.changeManager(projectID,managerID);
			System.out.println(managerChanged);
			if(managerChanged==true){
				return getProjectDeails(projectID);
			}		
			else{
				return new ModelAndView("mappingprojectfailed");
			}
					
		}
		
		
	
}
