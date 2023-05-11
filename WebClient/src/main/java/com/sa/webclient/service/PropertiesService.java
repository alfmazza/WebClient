package com.sa.webclient.service;

import com.sa.webclient.model.Properties;
import com.sa.webclient.util.ResourceNotFoundException;
import com.sa.webclient.util.BadRequestException;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PropertiesService implements IPropertiesService {
    
    @Autowired
    @Qualifier("webClient2")
    private WebClient webClient2;
    
    
    @Override
    public Flux<Properties> findAll() {
        
        return webClient2.get()
                        .uri("/properties")
                        .retrieve()
                        .onStatus(HttpStatus.NOT_FOUND::equals, response -> Mono.error(new ResourceNotFoundException("No se encontró el recurso. (Error 404)")))
                        .bodyToFlux(Properties.class)
                        .timeout(Duration.ofMillis(10_000));
    }

    
    @Override
    public Mono<Properties> findById(Long id) {
         return webClient2.get()
            .uri("/properties/" + id)
            .retrieve()
            .onStatus(HttpStatus.NOT_FOUND::equals, response -> Mono.error(new ResourceNotFoundException("No se encontró el recurso. (Error 404)")))
            .bodyToMono(Properties.class);
    }

    @Override
    public Mono<Properties> save (Properties proper) {
    
        return webClient2.post()
                        .uri("/properties")
                        .body(Mono.just(proper), Properties.class)
                        .retrieve()
                        .onStatus(HttpStatus.BAD_REQUEST::equals, response -> Mono.error(new BadRequestException("Intente nuevamente más tarde.(Error 400)")))
                        .bodyToMono(Properties.class);
    }
    
    
    @Override
    public Mono<Properties> delete(Long id){
        
        return webClient2.delete()
                        .uri("/properties/" + id)
                        .retrieve()
                        .onStatus(HttpStatus.NOT_FOUND::equals, response -> Mono.error(new ResourceNotFoundException("No se encontró el recurso. (Error 404)")))
                        .bodyToMono(Properties.class);
    }

    @Override
    public Mono<Properties> update(Long id, Properties proper) {
        return webClient2.put()
                        .uri("/properties/" + id)
                        .bodyValue(proper)
                        .retrieve()
                        .onStatus(HttpStatus.NOT_FOUND::equals, response -> Mono.error(new ResourceNotFoundException("No se encontró el recurso. (Error 404)")))
                        .onStatus(HttpStatus.BAD_REQUEST::equals, response -> Mono.error(new BadRequestException("Intente nuevamente más tarde.(Error 400)")))
                        .bodyToMono(Properties.class)
                        .timeout(Duration.ofMillis(10_000));
    }
}
