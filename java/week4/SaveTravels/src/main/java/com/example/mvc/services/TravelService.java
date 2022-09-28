package com.example.mvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.mvc.models.Travel;
import com.example.mvc.repositories.TravelRepository;

@Service
public class TravelService {
	private final TravelRepository travelRepository;
	
	public TravelService(TravelRepository travelRepository) {
		this.travelRepository = travelRepository;
		}
	
	public List<Travel> allTravels() {
        return travelRepository.findAll();
    }
    // creates a travel
    public Travel createTravel(Travel t) {
        return travelRepository.save(t);
    }
    // retrieves a travel
    public Travel findTravel(Long id) {
        Optional<Travel> optionalTravel = travelRepository.findById(id);
        if(optionalTravel.isPresent()) {
            return optionalTravel.get();
        } else {
            return null;
        }
		
		
	}
    
    //Update
    public Travel updateTravel(Travel travel) {
    	return travelRepository.save(travel);
    }
    //Delete
     public void  deleteTravel(Long id) {
    	 Optional<Travel> optionalTravel =travelRepository.findById(id);
    	 if(optionalTravel.isPresent()) {
    		 travelRepository.deleteById(id);
    	 	
    	 }
    	 
     }
	

}
