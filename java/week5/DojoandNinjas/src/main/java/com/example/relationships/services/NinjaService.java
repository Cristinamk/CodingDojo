package com.example.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.relationships.models.Ninja;
import com.example.relationships.repositories.NinjaRepository;

@Service
public class NinjaService {

	private final NinjaRepository ninjaRepository;
	
		public NinjaService(NinjaRepository ninjaRepository) {
		this.ninjaRepository = ninjaRepository;		}

		public List<Ninja> allNinja() {
			return ninjaRepository.findAll();
			}
		
//		Create dojo 
		public Ninja createNinja(Ninja oneninja) {
			return ninjaRepository.save(oneninja);
		}
//			retrieves a dojo
		public Ninja findNinja(Long id) {
			Optional<Ninja> optionalNinja = ninjaRepository.findById(id);
			if(optionalNinja.isPresent()) {
				return optionalNinja.get();
			} else {
				return null;
        }
		
		
	}
}
