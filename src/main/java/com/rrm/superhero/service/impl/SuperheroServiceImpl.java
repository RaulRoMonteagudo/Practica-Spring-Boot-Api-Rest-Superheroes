package com.rrm.superhero.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rrm.superhero.dto.SuperheroDTO;
import com.rrm.superhero.entity.Superhero;
import com.rrm.superhero.repository.SuperheroRepository;
import com.rrm.superhero.service.SuperheroService;
import com.rrm.superhero.utils.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SuperheroServiceImpl implements SuperheroService{
	
	@Autowired
	SuperheroRepository repository;

	@Override
	public List<SuperheroDTO> findAll() {
		
		log.info(Constants.INIT_METHOD_LOG);
		log.info(Constants.SUPERHERO_SERVICE_LOG + "findAll()");
		
		List<Superhero> superHeroList = repository.findAll();
		
		log.debug("repository.findAll(): " + superHeroList.toString());
		
		List<SuperheroDTO> superheroDTOList = new ArrayList<SuperheroDTO>();
	    
		superHeroList.forEach(superhero -> superheroDTOList.add(new SuperheroDTO(superhero.getId(), superhero.getName())));
		
		log.info(Constants.END_METHOD_LOG);
		
		return superheroDTOList;
	}

	@Override
	public SuperheroDTO findById(Long id) {
		
		log.info(Constants.INIT_METHOD_LOG);
		log.info(Constants.SUPERHERO_SERVICE_LOG + "findById()");
		
	    Optional<Superhero> superhero = repository.findById(id);
	    
	    SuperheroDTO superheroDTO;
	    
	    if(superhero.isPresent()) {
	    	superheroDTO = new SuperheroDTO(superhero.get().getId(), superhero.get().getName());
	    	log.debug("repository.findById: " + superhero.get().toString());
	    } else {
	    	log.debug("Superhero not found");
	    	superheroDTO = null;
	    }
	    
	    log.info(Constants.END_METHOD_LOG);
	    
		return superheroDTO;
	}

	@Override
	public List<SuperheroDTO> findByContainingText(String text) {
		
		log.info(Constants.INIT_METHOD_LOG);
		log.info(Constants.SUPERHERO_SERVICE_LOG + "findByContainingText(), text: " + text);
		
		List<Superhero> superHeroList = repository.findByNameContainingIgnoreCase(text);
		
		log.debug("repository.findByNameContaining(): " + superHeroList.toString());
		
		List<SuperheroDTO> superheroDTOList = new ArrayList<SuperheroDTO>();
	    
		superHeroList.forEach(superhero -> superheroDTOList.add(new SuperheroDTO(superhero.getId(), superhero.getName())));
		
		log.info(Constants.END_METHOD_LOG);
		
		return superheroDTOList;
	}

	@Override
	public SuperheroDTO update(SuperheroDTO superheroDTO) {
		
		log.info(Constants.INIT_METHOD_LOG);
		log.info(Constants.SUPERHERO_SERVICE_LOG + "update()");
		log.debug("SuperheroDTO: " + superheroDTO.toString());
		
		Superhero superhero = repository.findById(superheroDTO.getId()).orElseThrow();
		SuperheroDTO superheroDTOUpdated = new SuperheroDTO();
		
		if(superhero != null) {
			superhero.setId(superheroDTO.getId());
			superhero.setName(superheroDTO.getName());
			superhero = repository.save(superhero);
			repository.save(superhero);
			
			log.debug("Superhero: " + superheroDTO.toString());
			
			superheroDTOUpdated = new SuperheroDTO(superhero.getId(), superhero.getName());
		}

		log.info(Constants.END_METHOD_LOG);
		
		return superheroDTOUpdated;
	}

	@Override
	public void delete(Long id) {

		log.info(Constants.INIT_METHOD_LOG);
		log.info(Constants.SUPERHERO_SERVICE_LOG + "delete(), id: " + id);
		
		repository.deleteById(id);
		
		log.info(Constants.END_METHOD_LOG);
	}

}
