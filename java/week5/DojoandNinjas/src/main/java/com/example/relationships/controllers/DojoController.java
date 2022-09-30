package com.example.relationships.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.relationships.models.Dojo;
import com.example.relationships.services.DojoService;

@Controller
@RequestMapping("dojos")
public class DojoController {
	
	@Autowired 
	DojoService djService;
	
	@GetMapping("new")
	public String createDojoForm(@ModelAttribute("dojo") Dojo dojo) {
		return "index.jsp";
	}
	
	@PostMapping("new")
	public String createDojo(
					@Valid @ModelAttribute("dojo") Dojo dojo,
					BindingResult result) {
		if (result.hasErrors()) {
			return "index.jsp";
		}
		djService.saveDojo(dojo);
		return "redirect:/dojos/new";
	}
	@GetMapping ("/{id}")
	public String showDojo(@PathVariable Long id, Model model) {
		model.addAttribute("dojo", djService.findDojo(id));
		return "allninjas.jsp";
	}
}
