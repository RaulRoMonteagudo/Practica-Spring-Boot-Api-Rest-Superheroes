package com.rrm.superhero.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rrm.superhero.dto.SuperheroDTO;
import com.rrm.superhero.entity.Superhero;
import com.rrm.superhero.repository.SuperheroRepository;
import com.rrm.superhero.service.impl.CacheServiceImpl;
import com.rrm.superhero.service.impl.SuperheroServiceImpl;

@ExtendWith(MockitoExtension.class)
public class SuperheroServiceTest {

    @Mock
    private SuperheroRepository repository;
    
    @Mock
    private CacheServiceImpl cacheService;

    @InjectMocks
    private SuperheroServiceImpl service;

    private List<Superhero> superheroList;
    
    @BeforeEach
    public void setup(){
    	Superhero superhero;
    	superheroList = new ArrayList<>();

    	superhero = Superhero.builder()
                .id(1L)
                .name("Superman")
                .build();
    	
    	superheroList.add(superhero);
    	
    	superhero = Superhero.builder()
                .id(2L)
                .name("Spider-man")
                .build();
    	
    	superheroList.add(superhero);
    	
    	superhero = Superhero.builder()
                .id(3L)
                .name("Thor")
                .build();
    	
    	superheroList.add(superhero);
    }
    
    @Test
    public void whenFindAllSuperheroes_thenReturnSuperheroList(){

        when(repository.findAll()).thenReturn(superheroList);
        
        List<SuperheroDTO> superheroListTested = service.findAll();

        assertThat(superheroListTested).isNotNull();
        assertThat(superheroListTested.size()).isEqualTo(3);
    }
    
    @Test
    public void whenFindAllSuperheroesByContainingText_thenReturnSuperheroList(){
    	
    	// List that filters superheroes that contains "man"
    	List<Superhero> manList = superheroList.stream()
                .filter(x-> x.getName().toLowerCase().contains("man"))
                .collect(Collectors.toList());

        when(repository.findByNameContainingIgnoreCase("man")).thenReturn(manList);
        
        List<SuperheroDTO> superheroListTested = service.findByContainingText("man");

        assertThat(superheroListTested).isNotNull();
        assertThat(superheroListTested.size()).isEqualTo(2);
    }
    
    @Test
    public void whenFindSuperheroById_thenReturnSuperhero(){
    	
    	Superhero superhero = superheroList.get(0);
    	
        when(repository.findById(1L)).thenReturn(Optional.of(superhero));

        SuperheroDTO superheroFinded = service.findById(superhero.getId());

        assertThat(superheroFinded).isNotNull();
        assertThat(superheroFinded.getId().equals(1L));
    }
    
    @Test
    public void whenUpdateSuperhero_thenReturnUpdatedSuperhero(){
    	
    	Superhero superhero = superheroList.get(0);
    	SuperheroDTO superheroDTO = new SuperheroDTO(superhero.getId(), "Superwoman");
    	
    	when(repository.findById(superheroDTO.getId())).thenReturn(Optional.of(superhero));
    	when(repository.save(superhero)).thenReturn(superhero);
    	doNothing().when(cacheService).clearCache("");


        SuperheroDTO superheroUpdated = service.update(superheroDTO);

        assertThat(superheroUpdated.getName()).isEqualTo("Superwoman");
    }
    
    @Test
    public void whenDeleteSuperheroById_thenReturnNothing(){
    	
    	Long superheroId = superheroList.get(0).getId();
    	
    	doNothing().when(repository).deleteById(superheroId);
    	doNothing().when(cacheService).clearCache("");

        service.delete(superheroId);
        
        verify(repository, times(1)).deleteById(superheroId);
    }
}
