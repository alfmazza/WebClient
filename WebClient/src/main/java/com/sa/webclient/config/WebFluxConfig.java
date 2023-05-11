package com.sa.webclient.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
@EnableWebFlux
public class WebFluxConfig {
    
    @Value("${user.service.username}")
    private String username;
    
    @Value("${user.service.password}")
    private String password;
    
    @Value("${webclient.base-url-1}")
    private String url1;
    
    @Value("${webclient.base-url-2}")
    private String url2;

    
    @Bean
    @Qualifier("webClient1")
    public WebClient webClient1() {
        return newWebClient(url1, username, password);
    }
    
    @Bean
    @Qualifier("webClient2")
    public WebClient webclient2() {
        return newWebClient(url2, username, password);
    }
    
    
    org.slf4j.Logger logger = LoggerFactory.getLogger(WebFluxConfig.class);
    
    
    private WebClient newWebClient(String url, String username, String password) {
        
        HttpClient httpClient = HttpClient.create()
                .tcpConfiguration(client ->
                        client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                        .doOnConnected(conn -> conn
                                .addHandlerLast(new ReadTimeoutHandler(10))
                                .addHandlerLast(new WriteTimeoutHandler(10))));

        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient.wiretap(true));

        return WebClient.builder()
                        .baseUrl(url)
                        .defaultHeaders(header ->
                                header.setBasicAuth(username, password))
                        .clientConnector(connector)
                        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .build();
    } 
}
