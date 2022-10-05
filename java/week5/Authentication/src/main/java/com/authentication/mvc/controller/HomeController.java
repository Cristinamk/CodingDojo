package com.authentication.mvc.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.authentication.mvc.models.LoginUser;
import com.authentication.mvc.models.User;
import com.authentication.mvc.service.UserService;

@Controller

public class HomeController {
	@Autowired
	private  UserService userService;
	@GetMapping("/")
	public String index(Model model) {
	model.addAttribute("user",new User());
	model.addAttribute("loginUser",new LoginUser());
	
		return "index.jsp";
	}
	
	@PostMapping("/")
		
	public String registration(@Valid @ModelAttribute("user") User user,BindingResult result,HttpSession session,Model model) {
		
		User newUser= userService.registration(user, result);
		if (result.hasErrors()) {
			model.addAttribute("loginUser",new LoginUser());
			return "index.jsp";
		}else {
		session.setAttribute("loggedIn",newUser.getId());
		return "redirect:/dashboard";
		}
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute ("loginUser") LoginUser loginUser,BindingResult result,HttpSession session,Model model) {
		User loggedUser =userService.getOneByEmail(loginUser,result);
		if(result.hasErrors()) {
			model.addAttribute("user",new User());
			return "index.jsp";
		}else{session.setAttribute("loggedIn", loggedUser.getId());
			  return "redirect:/dashboard";
		}
		
	}
	
	
	@GetMapping("/dashboard")
	public String dashboard(Model model,HttpSession session) {
		model.addAttribute("user",userService.find((Long)session.getAttribute("loggedIn")));
		return "dashboard.jsp";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loggedIn");
		return "redirect:/";
	}
	

}


