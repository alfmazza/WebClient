package com.sa.webclient.controller;

import com.sa.webclient.model.User;
import com.sa.webclient.service.UserService;
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
public class UserController {
    
    @Autowired
    private UserService userService;
    
    
    @GetMapping("/user")
    public ResponseEntity<Flux<User>> findAll() {

        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }
    
    @GetMapping("/user/{id}")
    public ResponseEntity<Mono<User>> findById(@PathVariable("id") Long id) {
        
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }
    
    @PostMapping("/user")
    public ResponseEntity<Mono<User>> save(@RequestBody User proper) {
        
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(proper));
    }
    
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Mono<User>> deleteProperty(@PathVariable Long id) {
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.delete(id));
    }
    
    @PutMapping("/user/{id}")
    public ResponseEntity<Mono<User>> update(@PathVariable("id") Long id, @RequestBody User proper) {
    
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, proper));
    }
}
