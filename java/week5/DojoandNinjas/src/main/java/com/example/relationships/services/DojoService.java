package com.example.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.relationships.models.Dojo;
import com.example.relationships.repositories.DojoRepository;

@Service
public class DojoService {

	
	private final DojoRepository dojoRepository;
	
		public DojoService(DojoRepository dojoRepository) {
		this.dojoRepository = dojoRepository;		}

		public List<Dojo> allDojo() {
			return dojoRepository.findAll();
			}
		

//		Create dojo 
		public Dojo saveDojo(Dojo dojo) {
			return dojoRepository.save(dojo);
		}
//			retrieves a dojo
		public Dojo findDojo(Long id) {
			Optional<Dojo> optionalDojo = dojoRepository.findById(id);
			if(optionalDojo.isPresent()) {
				return optionalDojo.get();
			} else {
				return null;
        }
		
		
	}
}
