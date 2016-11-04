package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.domain.User;
import com.domain.repository.TaskRepository;
import com.domain.repository.UserRepository;

@Controller
public class LoginController {

	@Autowired
	TaskRepository taskRepo;
	@Autowired
	UserRepository userRepo;
	
	
	
}
