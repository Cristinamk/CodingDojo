package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerOm {
    @RequestMapping("/omikuji")
    public String index() {
        return "index.jsp";
    }
    
   @RequestMapping(value="/info",method=RequestMethod.POST)
   public String info(
		   
		   @RequestParam(value="number")String number,
		   @RequestParam(value="city")String city,
		   @RequestParam(value="person")String person,
		   @RequestParam(value="hobby")String hobby,
		   @RequestParam(value="thing")String thing,
		   @RequestParam(value="nice")String nice,
		   HttpSession session ) {
	   session.setAttribute("number", number);
	   session.setAttribute("city", city);
	   session.setAttribute("person", person);
	   session.setAttribute("hobby", hobby);
	   session.setAttribute("thing", thing);
	   session.setAttribute("nice", nice);
	 
   	return "redirect:/home";
   }
   
   @RequestMapping("/home")
   public String home() {
	   return "message.jsp";
   }

}
