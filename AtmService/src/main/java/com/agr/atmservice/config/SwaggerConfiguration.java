package com.agr.atmservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI(BuildProperties buildProperties) {
        return new OpenAPI()
                .info(metaData(buildProperties))
                .components(new Components())
                .paths(new Paths())
                .externalDocs(new ExternalDocumentation()
                        .url("https://www.test.com/")
                        .description("Test"));
    }

    private Info metaData(BuildProperties buildProperties) {
        return new Info()
                .title("Test ATM API ")
                .description("Test")
                .version(buildProperties.getVersion())
                .contact(new Contact()
                        .name("Gevorg Asatryan")
                        .url("")
                        .email("gev8919@gmail.com"));

    }
}
