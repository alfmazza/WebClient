package com.sa.user.controller;

import com.sa.user.model.User;
import com.sa.user.service.IUserService;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/user")
public class UserController {
    
    @Autowired
    private IUserService userService;
    
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try { 
            return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{\"notice\":\"Notice. No se encontro el registro.(CODE 204)\",\"timestamp\":\"" + LocalDateTime.now() + "\"}");
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getRecordById(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"notice\":\"Notice. No se encontro el registro.(CODE 404)\",\"timestamp\":\"" + LocalDateTime.now() + "\"}");
            
        }
    }
    
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody User user) {
        try {
             return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor, intente nuevamente mas tarde.(CODE 400)\",\"timestamp\":\"" + LocalDateTime.now() + "\"}");
        }
    }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody User user, BindingResult result) {
        
        if(result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor, intente nuevamente mas tarde.(CODE 400)\",\"timestamp\":\"" + LocalDateTime.now() + "\"}");
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, user));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. El registro no existe.(CODE 404)\",\"timestamp\":\"" + LocalDateTime.now() + "\"}");
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            boolean deleted = userService.delete(id);
            if(deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch(Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. El registro no existe.(CODE 404)\",\"timestamp\":\"" + LocalDateTime.now() + "\"}");
            }
    }
}
