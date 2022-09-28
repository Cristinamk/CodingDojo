package com.example.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/daikici")
public class HomeController {
	@RequestMapping("/travel/{city}")
	 public String welcome(@PathVariable("city")String city) {
		 return  "Congrats!You will soon Travel to " + city;
	 }
	
	@RequestMapping("lotto/{num}")
	public String lotto(@PathVariable("num") int num) {
		if (num%2==0) {
			return "You will take a grand journey in the near future, but be weary of tempting offers";
		}
		return "You have enjoyed the fruits of your labor but now is a great time to spend time with family and friends";
	}
	
		 
}
