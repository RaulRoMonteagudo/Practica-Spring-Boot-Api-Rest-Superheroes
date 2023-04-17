package com.rrm.superhero.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.rrm.superhero.service.CacheService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private CacheManager cacheManager;

    @Override
    public void clearCache(String cacheName) {
    	if(cacheName != null && !cacheName.isEmpty()) {
    		cacheManager.getCache(cacheName).clear();
    		log.info(cacheName + " cleared");
    	} else {
    		cacheManager.getCacheNames().forEach(c ->  cacheManager.getCache(c).clear());
    		log.info("All cache cleared");
    	}
    }
}