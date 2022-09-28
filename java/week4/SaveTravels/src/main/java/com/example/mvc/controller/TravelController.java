package com.example.mvc.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.mvc.models.Travel;
import com.example.mvc.services.TravelService;

@Controller
@RequestMapping("/expenses")
public class TravelController {
	
	@Autowired
	TravelService travelService;
	
	
	@RequestMapping("")
	public String home(@ModelAttribute("travel") Travel newtravel ,Model model ) {
	  List<Travel> alltravel = travelService.allTravels();
	  model.addAttribute("travels", alltravel);
	  return "index.jsp";
	}
	
	@PostMapping("")
	public String create(@Valid @ModelAttribute("travel") Travel travel, BindingResult result,Model model) {
		if (result.hasErrors()) {
			List<Travel> alltravel = travelService.allTravels();
			  model.addAttribute("travels", alltravel);
	      return "index.jsp";
			} else {
	      travelService.createTravel(travel);
	      return "redirect:/expenses";
			}

	}

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
     Travel alltravel = travelService.findTravel(id);
        model.addAttribute("travels", alltravel);
        return "edit.jsp";
    }
    @PutMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("travels") Travel travel, BindingResult result,Model model) {
        if (result.hasErrors()) {
            return "edit.jsp";
        } else {
            travelService.updateTravel(travel);
            return "redirect:/expenses";
        }
    
    }
    @RequestMapping("/display/{id}")
    public String index(@PathVariable("id") Long id ,Model model) {
        Travel thisTravel = travelService.findTravel(id);
        model.addAttribute("travel",thisTravel );
        return "show.jsp";
        		}
    
	@DeleteMapping("/delete/{id}")
	public String deleteExpense(@PathVariable("id") Long id) {
		travelService.deleteTravel(id);
		return "redirect:/expenses";
	}  
    
}


