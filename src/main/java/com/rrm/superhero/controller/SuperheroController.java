package com.rrm.superhero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rrm.superhero.dto.SuperheroDTO;
import com.rrm.superhero.service.SuperheroService;
import com.rrm.superhero.utils.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("superhero")
public class SuperheroController {

	@Autowired
	SuperheroService service;
	
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<SuperheroDTO>> getAllCompanies(@RequestParam(required = false) String text) {
    	
    	log.info(Constants.SUPERHERO_CONTROLLER_LOG + "getAllCompanies()");
    	
    	ResponseEntity<List<SuperheroDTO>> response;
    	
    	if(text == null) {
    		response = new ResponseEntity<List<SuperheroDTO>>(service.findAll(), HttpStatus.OK);
    	} else {
    		response = new ResponseEntity<List<SuperheroDTO>>(service.findByContainingText(text), HttpStatus.OK);
    	}
    	
        return response;
    }
	
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SuperheroDTO> getSuperhero(@PathVariable("id") Long id) {
    	
    	log.info(Constants.SUPERHERO_CONTROLLER_LOG + "getSuperhero()");
    	
    	ResponseEntity<SuperheroDTO> response = new ResponseEntity<SuperheroDTO>(service.findById(id), HttpStatus.OK);
    	
    	return response;
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<SuperheroDTO> updateSuperhero(@RequestBody SuperheroDTO superheroDTO) {
    	
    	log.info(Constants.SUPERHERO_CONTROLLER_LOG + "updateSuperhero()");
    	
    	ResponseEntity<SuperheroDTO> response = new ResponseEntity<SuperheroDTO>(service.update(superheroDTO), HttpStatus.OK);
    	
    	return response;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteSuperhero(@PathVariable("id") Long id) {
    	
    	log.info(Constants.SUPERHERO_CONTROLLER_LOG + "deleteSuperhero()");
    	
    	ResponseEntity<HttpStatus> response;
    	
    	try {
    		service.delete(id);
    		response = new ResponseEntity<HttpStatus>(HttpStatus.OK);
    	} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	
    	return response;
    }
}
