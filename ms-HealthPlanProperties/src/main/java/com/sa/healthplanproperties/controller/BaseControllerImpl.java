package com.sa.healthplanproperties.controller;

import com.sa.healthplanproperties.interfaces.BaseController;
import com.sa.healthplanproperties.model.Base;
import com.sa.healthplanproperties.service.BaseServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


public abstract class BaseControllerImpl<E extends Base, S extends BaseServiceImpl<E, Long>> implements BaseController<E, Long> {
    
    @Autowired
    protected S service;
   
    
    
    @GetMapping("")
    @Operation(
            description = "Obtener todos los registros",
            responses = {
                    @ApiResponse(responseCode = "200", ref = "okAPI"),
                    @ApiResponse(responseCode = "404", ref = "notFoundAPI"),              
            }
    )
    public ResponseEntity<?> getAllRecord() {
        try { 
            return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"notice\":\"Notice. No se encontraron registros.(CODE 404)\",\"timestamp\":\"" + LocalDateTime.now() + "\"}");
        }
    }
    
    
    @GetMapping("/paged")
    @Operation(
            description = "Obtener los registros paginados",
            responses = {
                    @ApiResponse(responseCode = "200", ref = "okAPI"),
                    @ApiResponse(responseCode = "404", ref = "notFoundAPI")
            }
    )
    public ResponseEntity<?> getAllRecord(Pageable pageable){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"notice\":\"Notice. No se encontraron registros.(CODE 404)\",\"timestamp\":\"" + LocalDateTime.now() + "\"}");
        }
    }
    
    
    
    @GetMapping("/{id}")
    @Operation(
            description = "Obtener un registro por id",
            responses = {
                    @ApiResponse(responseCode = "200", ref = "okAPI"),
                    @ApiResponse(responseCode = "404", ref = "notFoundAPI")
            }
    )
    public ResponseEntity<?> getRecordById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"notice\":\"Notice. No se encontro el registro.(CODE 404)\",\"timestamp\":\"" + LocalDateTime.now() + "\"}");
        }
    }
    
    
    
    @PostMapping("")
    @Operation(
            description = "Guardar registro/s",
            responses = {
                    @ApiResponse(responseCode = "201", ref = "createdAPI"),
                    @ApiResponse(responseCode = "400", ref = "badRequestAPI")
            }
    )
    public ResponseEntity<?> save(@RequestBody E entity) {
        try {
             return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor, intente nuevamente mas tarde.(CODE 400)\",\"timestamp\":\"" + LocalDateTime.now() + "\"}");
        }
    }
    
    
    
    @PutMapping("/{id}")
    @Operation(
            description = "Editar registro/s",
            responses = {
                    @ApiResponse(responseCode = "200", ref = "okAPI"),
                    @ApiResponse(responseCode = "400", ref = "badRequestAPI")
            }
    )
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody E entity, BindingResult result) {
        
        if(result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor, intente nuevamente mas tarde.(CODE 400)\",\"timestamp\":\"" + LocalDateTime.now() + "\"}");
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(id, entity));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. El registro no existe.(CODE 404)\",\"timestamp\":\"" + LocalDateTime.now() + "\"}");
        }
    }

    
    @DeleteMapping("/{id}")
    @Operation(
            description = "Eliminar registro/s",
            responses = {
                    @ApiResponse(responseCode = "204", ref = "noContentAPI"),
                    @ApiResponse(responseCode = "404", ref = "notFoundAPI")
            }
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {

        try {
            boolean deleted = service.delete(id);
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
