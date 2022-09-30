package com.example.relationships.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.relationships.models.Ninja;
import com.example.relationships.services.DojoService;
import com.example.relationships.services.NinjaService;

@RequestMapping("/ninjas")
@Controller
public class NinjaController {
	@Autowired 
	DojoService djService;
	
	@Autowired 
	NinjaService njService;
	

	@GetMapping("/new")
	public String createNinjaForm(@ModelAttribute("ninja") Ninja ninja, Model model) {
		model.addAttribute("dojo", djService.allDojo());
		return "newninja.jsp";
		}
	
	@PostMapping("/new")
	public String createNewNinja(Model model ,@Valid @ModelAttribute("ninja") Ninja ninja,BindingResult result) {
		if(result.hasErrors()) {
		model.addAttribute("dojo", djService.allDojo());
			return "newninja.jsp";
	}else {njService.createNinja(ninja);
		return "redirect:/ninjas/new";
	}
	}

}
