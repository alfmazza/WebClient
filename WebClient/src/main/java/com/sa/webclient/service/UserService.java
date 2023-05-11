package com.sa.webclient.service;

import com.sa.webclient.model.User;
import com.sa.webclient.util.BadRequestException;
import com.sa.webclient.util.ResourceNotFoundException;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService implements IUserService {

    @Autowired
    @Qualifier("webClient1")       
    private WebClient webClient1;
    
    @Override
    public Flux<User> findAll() {
        
        return webClient1.get()
			 .uri("/user")
			 .retrieve()
                         .onStatus(HttpStatus.NOT_FOUND::equals, response -> Mono.error(new ResourceNotFoundException("No se encontró el recurso. (Error 404)")))
			 .bodyToFlux(User.class)
			 .timeout(Duration.ofMillis(10_000));
    }

    @Override
    public Mono<User> findById(Long id) {
        
        return webClient1.get()
                        .uri("/user/" + id)
                        .retrieve()
                        .onStatus(HttpStatus.NOT_FOUND::equals, response -> Mono.error(new ResourceNotFoundException("No se encontró el recurso. (Error 404)")))
                        .bodyToMono(User.class);
    }

    @Override
    public Mono<User> save(User user) {
        
        return webClient1.post()
                        .uri("/user")
                        .body(Mono.just(user), User.class)
                        .retrieve()
                        .onStatus(HttpStatus.BAD_REQUEST::equals, response -> Mono.error(new BadRequestException("Intente nuevamente más tarde.(Error 400)")))
                        .bodyToMono(User.class);
    }

    @Override
    public Mono<User> delete(Long id) {
        
        return webClient1.delete()
                        .uri("/user/" + id)
                        .retrieve()
                        .onStatus(HttpStatus.NOT_FOUND::equals, response -> Mono.error(new ResourceNotFoundException("No se encontró el recurso. (Error 404)")))
                        .bodyToMono(User.class);
                                         
    }

    @Override
    public Mono<User> update(Long id, User user) {
        
        return webClient1.put()
                        .uri("/user/" + id)
                        .bodyValue(user)
                        .retrieve()
                        .onStatus(HttpStatus.NOT_FOUND::equals, response -> Mono.error(new ResourceNotFoundException("No se encontró el recurso. (Error 404)")))
                        .onStatus(HttpStatus.BAD_REQUEST::equals, response -> Mono.error(new BadRequestException("Intente nuevamente más tarde.(Error 400)")))
                        .bodyToMono(User.class)
                        .timeout(Duration.ofMillis(10_000));
    }
    
}
