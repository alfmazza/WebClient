package com.sa.webclient.controller;

import com.sa.webclient.model.Properties;
import com.sa.webclient.service.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1")
public class PropertiesController {
    
    
    @Autowired
    private PropertiesService properService;
    
   
    @GetMapping("/properties")
    public ResponseEntity<Flux<Properties>> findAll() {

        return ResponseEntity.status(HttpStatus.OK).body(properService.findAll());
    }
    
    @GetMapping("/properties/{id}")
    public ResponseEntity<Mono<Properties>> findById(@PathVariable("id") Long id) {
        
        return ResponseEntity.status(HttpStatus.OK).body(properService.findById(id));
    }
    
    @PostMapping("/properties")
    public ResponseEntity<Mono<Properties>> save(@RequestBody Properties proper) {
        
        return ResponseEntity.status(HttpStatus.CREATED).body(properService.save(proper));
    }
    
    @DeleteMapping("/properties/{id}")
    public ResponseEntity<Mono<Properties>> deleteProperty(@PathVariable Long id) {
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(properService.delete(id));
    }
    
    @PutMapping("/properties/{id}")
    public ResponseEntity<Mono<Properties>> update(@PathVariable("id") Long id, @RequestBody Properties proper) {
    
        return ResponseEntity.status(HttpStatus.OK).body(properService.update(id, proper));
    }

}
