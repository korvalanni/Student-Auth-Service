package ru.urfu.SecondLabTask.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


import java.util.Collections;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.urfu.SecondLabTask.controllers.api"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "User Management API",
                "This API provides endpoints for managing users. It allows operations such as retrieving a user's details, adding a new user, updating user information, and deleting a user.", // Description
                "1.0",
                "Terms of service URL (if any)",
                new Contact("Your Name or Your Organization's Name", "Your Website URL", "Your Email"),
                "API License",
                "API License URL",
                Collections.emptyList()
        );
    }


}
