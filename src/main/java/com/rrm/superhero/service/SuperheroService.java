package com.rrm.superhero.service;

import java.util.List;

import com.rrm.superhero.dto.SuperheroDTO;

public interface SuperheroService {

	List<SuperheroDTO> findAll();
	SuperheroDTO findById(Long id);
	List<SuperheroDTO> findByContainingText(String text);
	SuperheroDTO update(SuperheroDTO superheroDTO);
	void delete(Long id);
	
}
