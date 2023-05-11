package com.sa.healthplanproperties.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

@OpenAPIDefinition
@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI getApiInfo() {
        
        ApiResponse okAPI = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE, 
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" : \"Traido con exito!\"}")))
        );
        
        ApiResponse notFoundAPI = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE, 
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("{\"code\" : 404, \"Status\" : \"Not found!\", \"Message\" : \"No se encontraron registros.\"}")))
        );
        
         ApiResponse badRequestAPI = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE, 
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("{\"code\" : 400, \"Status\" : \"Bad Request!\", \"Message\" : \"Error. Por favor, intente nuevamente mas tarde.\"}")))
        );   
         
        ApiResponse createdAPI = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE, 
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("{\"code\" : 201, \"Status\" : \"Created!\", \"Message\" : \"Guardado con exito!\"}")))
        );
        
        ApiResponse noContentAPI = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE, 
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("{\"code\" : 204, \"Status\" : \"No content!\", \"Message\" : \"Eliminado con exito!\"}")))
        );
         
        Components components = new Components();
        components.addResponses("okAPI", okAPI);
        components.addResponses("notFoundAPI", notFoundAPI);
        components.addResponses("badRequestAPI", badRequestAPI);
        components.addResponses("createdAPI", createdAPI);
        components.addResponses("noContentAPI", noContentAPI);
        
        //http://localhost:8081/swagger-ui/index.html
        
        return new OpenAPI().components(components)
                            .info(new Info().title("Health plan properties API")
                            .version("Version: 2.0")
                            .description("API for managing health plan properties, including creating and editing properties information.")
                            .termsOfService("http://www.alpi.com/terminos")
                            .contact(new Contact()
                            .name("Alfredo Mazza")
                            .email("alfredolmazza@gmail.com")
                            .url("http://www.alpi.com/contact"))
        );
        
     }
}