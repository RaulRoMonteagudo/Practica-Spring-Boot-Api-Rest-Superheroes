package com.rrm.superhero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rrm.superhero.service.CacheService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private CacheService service;

    @GetMapping("/clear")
	@Operation(description = "Clear specified cache with given cache param. If param is empty clears all cache")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "OK")
	})
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> clearCache(@RequestParam(required = false) String cacheName){
    	service.clearCache(cacheName);
        return new ResponseEntity<>("Cache cleared", HttpStatus.OK);
    }
}
