package com.rrm.superhero;

import java.util.Base64;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class SuperheroIntegrationTest {
	
    @Autowired
    private MockMvc mockMvc;
    
    private String encodedTestUserPassword = "Basic ".concat(
    		Base64.getEncoder().encodeToString("user:user".getBytes()));
    
    @Test
    void whenFindAllSuperheroesEndpoint_thenReturnSuperheroList() throws Exception {
    	
        mockMvc.perform(MockMvcRequestBuilders.get("/superhero"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    	
        mockMvc.perform(MockMvcRequestBuilders.get("/superhero")
        		.header("Authorization", encodedTestUserPassword))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(10)));
    }
    
    @Test
    void whenFindAllSuperheroesByContainingTextEndpoint_thenReturnSuperheroList() throws Exception {
    	
        mockMvc.perform(MockMvcRequestBuilders.get("/superhero?text=man"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    	
        mockMvc.perform(MockMvcRequestBuilders.get("/superhero?text=man")
        		.header("Authorization", encodedTestUserPassword))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(5)));
    }
    
    @Test
    void whenFindSuperheroByIdEndpoint_thenReturnSuperhero() throws Exception {
    	
        mockMvc.perform(MockMvcRequestBuilders.get("/superhero/1"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    	
        mockMvc.perform(MockMvcRequestBuilders.get("/superhero/1")
        		.header("Authorization", encodedTestUserPassword))
                .andExpect(MockMvcResultMatchers.status().isOk())             
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Superman")));
    }
    
    @Test
    void whenUpdateSuperheroEndpoint_thenReturnUpdatedSuperhero() throws Exception {
        String body = "{\n"
        		+ "    \"id\": 1,\n"
        		+ "    \"name\": \"Superwoman\"\n"
        		+ "}";
        
        mockMvc.perform(MockMvcRequestBuilders.put("/superhero")
        		.content(body)
        		.contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    	
        mockMvc.perform(MockMvcRequestBuilders.put("/superhero")
        		.content(body)
        		.contentType(MediaType.APPLICATION_JSON)
        		.header("Authorization", encodedTestUserPassword))
                .andExpect(MockMvcResultMatchers.status().isOk())             
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Superwoman")));
    }
    
    @Test
    void whenDeleteSuperheroByIdEndpoint_thenReturnNothing() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/superhero/1"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    	
        mockMvc.perform(MockMvcRequestBuilders.delete("/superhero/1")
        		.header("Authorization", encodedTestUserPassword))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}