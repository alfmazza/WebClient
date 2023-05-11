package com.sa.healthplanproperties.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //de disablea el csrf por lo menos en etapa de desarrollo. En produccion se habilita
        http.csrf().disable();
        //el any request pide auth para cada recurso
        //and para agregar algo mas, httpbasic pq es el que se usa, en otro caso se pone ese
        http.authorizeHttpRequests()
                //.antMatchers("/actuator/**").hasRole("ACTUATOR_ADMIN")
                .anyRequest()
                .authenticated() //true cuando autentica
                .and()
                .httpBasic();
        
        return http.build();
    }
}
