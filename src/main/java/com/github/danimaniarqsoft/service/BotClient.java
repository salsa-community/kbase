package com.github.danimaniarqsoft.service;

import com.github.danimaniarqsoft.service.dto.CacheDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class BotClient {

    private final Logger log = LoggerFactory.getLogger(BotClient.class);
        
    @Autowired
    private RestTemplate rest;


    @Async
    public void cleanCache() {
        try {
            rest.put("/api/cache/flush", new CacheDTO("all"), String.class);
        } catch (RestClientException e) {
         log.error("clean cache", e);;
        }
    }
    
}
