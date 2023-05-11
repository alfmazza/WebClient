package com.sa.healthplanproperties.controller;

import com.sa.healthplanproperties.model.Properties;
import com.sa.healthplanproperties.service.PropertiesServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/properties")

public class PropertiesController extends BaseControllerImpl<Properties, PropertiesServiceImpl> {

    @Autowired
    private PropertiesServiceImpl propertiesService;
    /*
    Para pasar el valor en postman (key:filter value: string)
    */
    @GetMapping("/search")
    @Operation(
            description = "Obtener registros por filtro",
            responses = {
                    @ApiResponse(responseCode = "200", ref = "okAPI"),
                    @ApiResponse(responseCode = "404", ref = "notFoundAPI"),              
            }
    )
    public ResponseEntity<?> search(@RequestParam String filter) {
        
        try {
            return ResponseEntity.status(HttpStatus.OK).body(propertiesService.search(filter));
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"notice\":\"Notice. No se encontraron registros.(CODE 404)\",\"timestamp\":\"" + LocalDateTime.now() + "\"}");
        }
    }

    
    @GetMapping("/searchPaged")
    @Operation(
            description = "Obtener registros filtrados y paginados",
            responses = {
                    @ApiResponse(responseCode = "200", ref = "okAPI"),
                    @ApiResponse(responseCode = "404", ref = "notFoundAPI")
            }
    )
    public ResponseEntity<?> search(@RequestParam String filter, Pageable pageable) {
    
        try {
            return ResponseEntity.status(HttpStatus.OK).body(propertiesService.search(filter, pageable));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"notice\":\"Notice. No se encontraron registros.(CODE 404)\",\"timestamp\":\"" + LocalDateTime.now() + "\"}");
        }
    }

    @Override
    public ResponseEntity<?> update(Long id, Properties entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
