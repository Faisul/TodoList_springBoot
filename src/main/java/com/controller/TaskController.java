package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import com.domain.Task;
import com.domain.User;
import com.domain.repository.TaskRepository;
import com.domain.repository.UserRepository;

@Controller("task")
@SessionAttributes("user")
public class TaskController {
	@Autowired
	TaskRepository taskRepo;
	@Autowired
	UserRepository userRepo;
	
    @RequestMapping("/todo")
    public String greeting(@RequestParam(value="msg", required=false) String msg, WebRequest request, Model model) {
    	User u= userRepo.findById(new Long(request.getAttribute("userId", WebRequest.SCOPE_SESSION).toString()));
    	List<Task> msgs = taskRepo.findByUserId(u.getId());
        model.addAttribute("msgs", msgs);
        return "greeting";
    }
    
    @RequestMapping("/addTask")
    public String addTask(@RequestParam(value="msg", required=true) String msg, @RequestParam(value="date", required=false) String date,WebRequest request, Model model) throws ParseException {

    	Task task;
    	if(msg!=null && !msg.isEmpty()){
    		 task= new Task();
    		 task.setText(msg);
    		 task.setStatus("In Progress");
    		 
    		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    		 String tomorrow = LocalDate.now().plusDays(1L).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    		 Date temp;
    		 if(!date.isEmpty())
    			 temp = sdf.parse(date);
    		 else{
    			 temp = sdf.parse(tomorrow);
    		 }
    		 
    		 task.setDueDate(temp);
    		 User u= userRepo.findById(new Long(request.getAttribute("userId", WebRequest.SCOPE_SESSION).toString()));
    		 task.setUser(u);
    		 taskRepo.save(task);
    	}
        return "redirect:todo";
    }

    @RequestMapping("/complete")
    public String completeTask(@RequestParam(value="user", required=true) String user,@RequestParam(value="id", required=true) String id,@RequestParam(value="action", required=true) String action, Model model) {
    	
    	Task task= taskRepo.findById(new Long(id));
    	
    	if(action.equals("c"))
    		task.setStatus("Complete");
    	else if(action.equals("u"))
      		task.setStatus("In Progress");
    	
    	taskRepo.save(task);
		return "redirect:todo";
    	
    }
    
}
