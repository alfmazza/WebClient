package com.sa.webclient.service;

import com.sa.webclient.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface IUserService {
    
    Flux<User> findAll();
    Mono<User> findById(Long id);
    Mono<User> save(User user);
    Mono<User> update(Long id, User user);
    Mono<User> delete(Long id);
}
