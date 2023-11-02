package com.minsait.AppContacts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
        		.components(new Components().addSecuritySchemes("basicScheme",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
	            .info(new Info()
	                .title("Contacts Application")
	                .version("1.0")
	                .description("API made to consolidate knowledge obtained in Minsait's Java Training course.")
	                .contact(new Contact()
		                    .name("Itaji de Carvalho")
		                    .url("https://github.com/itaji-create")
		                    .email("ctt.itaji.carvalho@gmail.com")
		             )
	             );
    }
}
