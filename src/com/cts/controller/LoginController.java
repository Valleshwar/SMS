package com.cts.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.cts.bean.UserBean;
import com.cts.service.ILoginService;


@Controller
@RequestMapping(value="/login")
public class LoginController {
	
	private UserBean userBean;
	
	@Autowired
	private ILoginService loginService;
	
	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init() {
		userBean = new UserBean();
		return new ModelAndView("login", "loginDetails", userBean);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView doLogin(@ModelAttribute("loginDetails") @Valid UserBean userBean,BindingResult result,
			HttpServletRequest request,	HttpServletResponse response)
	{		
		try{	
		
	
		ValidationUtils.rejectIfEmptyOrWhitespace(result,"userID","userID.required", "UserID can not be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(result,"password","password.required", "Password not be empty");

		if(result.hasErrors()){
			return new ModelAndView("login", "loginDetails", userBean);
		}
		else
		{
			List<String> details=loginService.authenticateUser(userBean);
		
			if(details.size()==0)
			{
				result.addError(new ObjectError("Invalid", "Invalid credentials. " +
						"Username or Password is incorrect."));
				return new ModelAndView("login", "loginDetails", userBean);				
			}
			else
			{
				String role=details.get(0).toString();
					
				if(role.equalsIgnoreCase("admin"))
				{
					request.getSession().setAttribute("userID", userBean);
					RedirectView redirectView=new RedirectView("adminhome.do",true);
					return new ModelAndView(redirectView);				
				}
				else if(role.equalsIgnoreCase("Manager"))
				{
					request.getSession().setAttribute("userID", userBean);
					RedirectView redirectView=new RedirectView("managerhome.do",true);
					return new ModelAndView(redirectView);				
				}
				else if(role.equalsIgnoreCase("Associate"))
				{
					request.getSession().setAttribute("userID", userBean);
					RedirectView redirectView=new RedirectView("employeehome.do",true);
					return new ModelAndView(redirectView);				
				}
				else{			
						result.addError(new ObjectError("Invalid", "Invalid credentials. " +
								"Username or Password is incorrect."));
						return new ModelAndView("login", "loginDetails", userBean);
				}
			}
		}
		}catch (Exception e) {
			System.out.println("Exception in LoginController "+e.getMessage());
			e.printStackTrace();
			return new ModelAndView("login", "loginDetails", userBean);	
	}
	
  }
	

}
