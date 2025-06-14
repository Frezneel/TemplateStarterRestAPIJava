package com.frezneel.starter.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                // Informasi dasar API
                .info(new Info()
                        .title("${springdoc.info.title}")
                        .version("${springdoc.info.version}")
                        .description("${springdoc.info.description}")
                        .termsOfService("${springdoc.info.terms-of-service}")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("${springdoc.info.contact.name}")
                                .url("${springdoc.info.contact.url}")
                                .email("${springdoc.info.contact.email}"))
                        .license(new io.swagger.v3.oas.models.info.License()
                                .name("${springdoc.info.license.name}")
                                .url("${springdoc.info.license.url}"))
                )
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                //Tipe skema adalah HTTP
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                );
    }
}
