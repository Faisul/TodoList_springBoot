package com.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.domain.User;
import com.domain.repository.TaskRepository;
import com.domain.repository.UserRepository;

@Controller("loginCont")
public class LoginController {

	@Autowired
	TaskRepository taskRepo;
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping("/")
    public String goHome(ModelAndView model) {
        return "Login";
    }
	
	@RequestMapping("/signup")
    public String newUser(Model model) {
        return "SignUp";
    }
	
    @RequestMapping("/login")
    public ModelAndView login(@RequestParam(value="user", required=true) String name,@RequestParam(value="password", required=true) String password,WebRequest request, HttpSession session, ModelAndView model) {
    	User user = userRepo.findByUserName(name);
    	if(null != user){
    		if(password.equals(user.getPassword())){
    			request.setAttribute("userId", user.getId(), WebRequest.SCOPE_SESSION);
    			session.setAttribute("user", WordUtils.capitalize(user.getUserName()));
    		}
    	}else{
    		model.addObject("error", "Invalid Details");
    		model.setViewName("/login");
    	}
		model.setViewName("redirect:/todo");
		return model;
    }
    
    @RequestMapping("/newUser")
    public String createuser(@RequestParam(value="name", required=true) String name
    		,@RequestParam(value="fname", required=true) String fname
    		,@RequestParam(value="lname", required=true) String lname
    		,@RequestParam(value="email", required=true) String email
    		,@RequestParam(value="password", required=true) String password
    		, Model model) {
				
		if(!(null!=name && null!= fname && null!=lname && null!=email && null!=password) )
		{
			model.addAttribute("error", "Invalid Details");
			return "SignUp";
		}
		User x = userRepo.findByUserName(name);
		if(x == null){
			User user = new User(name, fname, lname, email, password);
			userRepo.save(user);
		}else{
			model.addAttribute("error", "UserName already exists");
			return "SignUp";
		}
		
		return "Login";
	}
    
    @RequestMapping("/logout")
    public String logout( WebRequest request, Model model,HttpSession session) {
    	session.invalidate();
    	return "Login";
    }
}
