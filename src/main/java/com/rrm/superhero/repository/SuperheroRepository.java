package com.rrm.superhero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rrm.superhero.entity.Superhero;

@Repository
public interface SuperheroRepository extends JpaRepository<Superhero, Long>{

	List<Superhero> findByNameContainingIgnoreCase(String name);
}
