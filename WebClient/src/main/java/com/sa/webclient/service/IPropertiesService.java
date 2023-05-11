package com.sa.webclient.service;

import com.sa.webclient.model.Properties;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface IPropertiesService {
    
    
    Flux<Properties> findAll();
    Mono<Properties> findById(Long id);
    Mono<Properties> save(Properties proper);
    Mono<Properties> update(Long id, Properties proper);
    Mono<Properties> delete(Long id);
}
